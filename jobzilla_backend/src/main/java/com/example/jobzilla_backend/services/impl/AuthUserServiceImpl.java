package com.example.jobzilla_backend.services.impl;

import com.example.jobzilla_backend.dtos.LoginUserDto;
import com.example.jobzilla_backend.dtos.RegisterUserDto;
import com.example.jobzilla_backend.entities.Role;
import com.example.jobzilla_backend.entities.User;
import com.example.jobzilla_backend.exceptions.JobApiException;
import com.example.jobzilla_backend.repositories.RoleRepository;
import com.example.jobzilla_backend.repositories.UserRepository;
import com.example.jobzilla_backend.security.CustomUserDetailsService;
import com.example.jobzilla_backend.security.JwtTokenProvider;
import com.example.jobzilla_backend.services.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserDetailsService customUserDetailsService;

    public AuthUserServiceImpl(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    public String LoginUser(LoginUserDto loginUserDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDto.getUsernameOrEmail(), loginUserDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String registerUser(RegisterUserDto registerUserDto) {

        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
            throw new JobApiException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new JobApiException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setPhone(registerUserDto.getPhone());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER").get();

        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!.";
    }

    @Override
    public String registerRecruiter(RegisterUserDto registerUserDto) {

        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
            throw new JobApiException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new JobApiException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setPhone(registerUserDto.getPhone());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("RECRUITER").get();

        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "Recruiter registered successfully!.";


    }
}
