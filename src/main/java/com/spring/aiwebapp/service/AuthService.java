package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.request.AuthRequest;
import com.spring.aiwebapp.DTO.request.UserRequestForRegister;
import com.spring.aiwebapp.DTO.response.AuthResponse;
import com.spring.aiwebapp.DTO.response.UserDTO;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.AuthenticationFailedException;
import com.spring.aiwebapp.mapper.UserMapper;
import com.spring.aiwebapp.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

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

            UserDTO userDTO = UserMapper.toUserDTO(user);

            return AuthResponse.builder()
                    .token(jwt)
                    .userResponse(userDTO)
                    .build();
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Invalid email or password");
        }
    }

    public AuthResponse register(UserRequestForRegister userRequestForRegister) {
        UserDTO createdUser = userService.createUser(userRequestForRegister);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestForRegister.getEmail(), // Email is used as the username for authentication
                            userRequestForRegister.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(createdUser.getEmail());
            String jwt = jwtTokenUtil.generateToken(userDetails);

            return AuthResponse.builder()
                    .token(jwt)
                    .userResponse(createdUser)
                    .build();
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException ("Registration successful but failed to authenticate");
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
