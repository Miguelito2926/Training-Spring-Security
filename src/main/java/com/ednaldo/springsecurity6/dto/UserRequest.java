package com.ednaldo.springsecurity6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String username;
    private String password;
    private String nameRole;

}
