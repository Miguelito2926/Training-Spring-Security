package com.ednaldo.springsecurity6.controller;

import com.ednaldo.springsecurity6.dto.UserRequest;
import com.ednaldo.springsecurity6.dto.UserResponse;
import com.ednaldo.springsecurity6.entities.User;
import com.ednaldo.springsecurity6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws RoleNotFoundException {
        // Chama o serviço para criar o usuário
        User user = userService.createUser(userRequest);

        // Converte a entidade User em um UserResponse para retornar ao cliente
        UserResponse userResponse = new UserResponse(user.getUserId(), user.getUsername(), user.getRoles());

        // Retorna a resposta com status 201 (Created) e o corpo com os dados do usuário criado
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}