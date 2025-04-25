package com.spring.aiwebapp.config;

import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {
        if (!userRepository.existsByEmail("ashrafov@gmail.com")) {
            User admin = new User();
            admin.setFirstName("Murad");
            admin.setLastName("Ashrafov");
            admin.setEmail("ashrafov@gmail.com");
            admin.setPassword(passwordEncoder.encode("Murad.123"));
            admin.setRoles(Set.of(User.Role.ROLE_ADMIN));
            userRepository.save(admin);
        }
    }
}
