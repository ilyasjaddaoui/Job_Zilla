package com.example.jobzilla_backend.services;

import com.example.jobzilla_backend.dtos.LoginUserDto;
import com.example.jobzilla_backend.dtos.RegisterUserDto;
import com.example.jobzilla_backend.entities.User;

public interface AuthUserService {
    String LoginUser(LoginUserDto loginUserDto);
    String registerUser(RegisterUserDto registerUserDto);
    String registerRecruiter(RegisterUserDto registerUserDto);
}
