package com.example.jobzilla_backend.security;

import com.example.jobzilla_backend.entities.User;
import org.springframework.security.core.GrantedAuthority;


import java.util.Set;

public class CustomUserDetailsWithId extends org.springframework.security.core.userdetails.User {
    private final Long userId;

    public CustomUserDetailsWithId(User user, Long userId, Set<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
