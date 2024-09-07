package com.ednaldo.springsecurity6.config;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PublicEndpoints {

    public List<String> getEndpoints() {
        return Arrays.asList(
               // "/users/**",
                "/auth/login"
                // Adicione mais endpoints aqui
        );
    }
}
