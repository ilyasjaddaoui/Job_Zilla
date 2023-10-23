package com.example.jobzilla_backend.web;

import com.example.jobzilla_backend.dtos.JwtAuthResponse;
import com.example.jobzilla_backend.dtos.LoginUserDto;
import com.example.jobzilla_backend.dtos.RegisterUserDto;



import com.example.jobzilla_backend.security.CustomUserDetailsService;
import com.example.jobzilla_backend.services.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthUserController {

    private AuthUserService authUserService;
    private CustomUserDetailsService customUserDetailsService;

    public AuthUserController(CustomUserDetailsService customUserDetailsService ,AuthUserService authUserService) {
        this.authUserService = authUserService;
        this.customUserDetailsService = customUserDetailsService;
    }


    // build login REST API
    @PostMapping("/user/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginUserDto loginUserDto){
        String token = authUserService.LoginUser(loginUserDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto){
        String response = authUserService.registerUser(registerUserDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Register REST API
    @PostMapping("/recruiter/register")
    public ResponseEntity<String> registerRecruiter(@RequestBody RegisterUserDto registerUserDto){
        String response = authUserService.registerRecruiter(registerUserDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/current")
    public User getCurrentUser(Principal principal){
        return ((User) this.customUserDetailsService.loadUserByUsername(principal.getName()));
    }

}
