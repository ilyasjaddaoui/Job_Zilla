package com.example.jobzilla_backend.dtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String usernameOrEmail;
    private String password;
}
