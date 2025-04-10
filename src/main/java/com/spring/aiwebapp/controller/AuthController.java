package com.spring.aiwebapp.controller;

import com.spring.aiwebapp.DTO.request.AuthRequest;
import com.spring.aiwebapp.DTO.request.UserRequest;
import com.spring.aiwebapp.DTO.response.AuthResponse;
import com.spring.aiwebapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRequest userDTO) {
        AuthResponse response = authService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
