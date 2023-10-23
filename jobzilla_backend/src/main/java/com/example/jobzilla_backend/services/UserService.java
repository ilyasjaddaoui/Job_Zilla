package com.example.jobzilla_backend.services;

import com.example.jobzilla_backend.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto, Long userId);
    void deleteUser(Long userId);
}
