package com.example.todoapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class LoginDto {
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;
}
