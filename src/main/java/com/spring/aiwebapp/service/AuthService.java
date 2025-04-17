package com.spring.aiwebapp.service;

import com.spring.aiwebapp.DTO.request.AuthRequest;
import com.spring.aiwebapp.DTO.request.UserRequest;
import com.spring.aiwebapp.DTO.response.AuthResponse;
import com.spring.aiwebapp.DTO.response.UserDTO;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.mapper.UserMapper;
import com.spring.aiwebapp.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), // Email is used as the username for authentication
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(user);

            UserDTO userDTO = userMapper.toResponseDTO(user);

            return AuthResponse.builder()
                    .token(jwt)
                    .userResponse(userDTO)
                    .build();
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException ("Invalid email or password");
        }
    }

    public AuthResponse register(UserRequest userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(), // Email is used as the username for authentication
                            userDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(user);

            return AuthResponse.builder()
                    .token(jwt)
                    .userResponse(createdUser)
                    .build();
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException ("Registration successful but failed to authenticate");
        }
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof User)) {
            throw new IllegalStateException("User is not authenticated");
        }
        return (User) authentication.getPrincipal();
    }


}
