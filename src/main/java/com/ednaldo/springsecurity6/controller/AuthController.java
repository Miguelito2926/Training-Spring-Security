package com.ednaldo.springsecurity6.controller;

import com.ednaldo.springsecurity6.dto.LoginRequest;
import com.ednaldo.springsecurity6.dto.LoginResponse;
import com.ednaldo.springsecurity6.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping(value= "auth")
public class AuthController {

    private final AuthorizationService authorizationService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Chama o serviço de autorização para realizar o login
        var response = authorizationService.login(request);

        // Retorna a resposta com o status 200 (OK)
        return ResponseEntity.ok(response);
    }
}