package com.spring.aiwebapp.service;
import com.spring.aiwebapp.DTO.request.UserRequestForRegister;
import com.spring.aiwebapp.DTO.request.UserRequestForUpdate;
import com.spring.aiwebapp.DTO.response.UserDTO;
import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.exception.UserAlreadyExistsException;
import com.spring.aiwebapp.mapper.UserMapper;
import com.spring.aiwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(UserRequestForRegister userRequestForRegister) {
        if (userRepository.existsByEmail(userRequestForRegister.getEmail())) throw
                new UserAlreadyExistsException("User already exists with email: " + userRequestForRegister.getEmail());

        User user = UserMapper.toEntity(userRequestForRegister);
        user.setPassword(passwordEncoder.encode(userRequestForRegister.getPassword()));
        user.setRoles(Set.of(User.Role.ROLE_USER));

        User savedUser = userRepository.save(user);
        return UserMapper.toUserDTO(savedUser);
    }

    @Transactional
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return UserMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return UserMapper.toUserDTO(user);
    }

    @Transactional
    public void updateUser(Long id, UserRequestForUpdate userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException ("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
