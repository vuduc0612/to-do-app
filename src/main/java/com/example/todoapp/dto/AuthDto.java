package com.example.todoapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthDto {
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String username;
}
