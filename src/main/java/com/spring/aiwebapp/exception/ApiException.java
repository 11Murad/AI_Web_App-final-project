package com.spring.aiwebapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
