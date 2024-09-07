package com.ednaldo.springsecurity6.service;

import com.ednaldo.springsecurity6.dto.LoginRequest;
import com.ednaldo.springsecurity6.dto.LoginResponse;
import com.ednaldo.springsecurity6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    @Value("${jwt.expires}")
    private Long jwtExpires;

    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByUsername(request.username());
        if (user.isEmpty() || !user.get().isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid");
        }

        // Construção das claims do JWT
        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getUserId().toString())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(jwtExpires))
                .claim("username", user.get().getUsername())  // Adiciona o username nas claims, se necessário
                .build();

        // Geração do token JWT
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // Retorna a resposta com o token gerado
        return new LoginResponse(jwtValue, jwtExpires.toString());
    }
}
