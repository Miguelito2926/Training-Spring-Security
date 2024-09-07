package com.ednaldo.springsecurity6.dto;

import com.ednaldo.springsecurity6.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    private UUID id;
    private String username;
    private Set<Role> roles;
}
