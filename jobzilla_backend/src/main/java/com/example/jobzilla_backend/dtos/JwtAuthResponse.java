package com.example.jobzilla_backend.dtos;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthResponse() {
        this.token = token;
    }
}
