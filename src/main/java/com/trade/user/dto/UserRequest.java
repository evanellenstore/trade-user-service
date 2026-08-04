package com.trade.user.dto;

import com.trade.user.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role;

    private Boolean active;
}
