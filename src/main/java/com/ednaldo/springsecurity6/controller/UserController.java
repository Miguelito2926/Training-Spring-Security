package com.ednaldo.springsecurity6.controller;

import com.ednaldo.springsecurity6.dto.CreateUserProfileRequest;
import com.ednaldo.springsecurity6.dto.CreateUserProfileResponse;
import com.ednaldo.springsecurity6.entities.User;
import com.ednaldo.springsecurity6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserProfileResponse> createUser(@RequestBody CreateUserProfileRequest userRequest) throws RoleNotFoundException {
        // Chama o serviço para criar o usuário
        User user = userService.createUser(userRequest);

        // Converte a entidade User em um UserResponse para retornar ao cliente
        CreateUserProfileResponse userResponse = new CreateUserProfileResponse(user.getUserId(), user.getUsername(), user.getRoles());

        // Retorna a resposta com status 201 (Created) e o corpo com os dados do usuário criado
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        List<User> user = userService.findAll();
        return ResponseEntity.ok(user);
    }
}