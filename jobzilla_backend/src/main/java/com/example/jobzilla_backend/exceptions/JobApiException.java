package com.example.jobzilla_backend.exceptions;

import org.springframework.http.HttpStatus;

public class JobApiException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public JobApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
