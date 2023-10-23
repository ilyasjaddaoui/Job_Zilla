package com.example.jobzilla_backend.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String username;
    private String email;
    private String password;
    private String phone;
}
