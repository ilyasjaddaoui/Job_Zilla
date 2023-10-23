package com.example.jobzilla_backend.dtos;


import lombok.Data;



@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
//    private Set<Role> roles;
}
