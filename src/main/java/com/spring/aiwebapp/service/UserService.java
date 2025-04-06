package com.spring.aiwebapp.service;

import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.mapper.UserMapper;
import com.spring.aiwebapp.DTO.request.UserRequest;
import com.spring.aiwebapp.DTO.response.Result;
import com.spring.aiwebapp.DTO.response.UserResponse;
import com.spring.aiwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepo;

    public void createUser(UserRequest userRequest) {
        User user = UserMapper.userRequestToUser(userRequest);
        userRepo.save(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("User is not found"));
        return UserMapper.userToUserResponse(user);
    }

    public Result<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepo.findAll(pageable);
        List<UserResponse> userResponses = users.getContent().stream()
                .map(UserMapper::userToUserResponse).toList();
        return new Result<UserResponse>(userResponses, page, size, users.getTotalPages());
    }

    public void deleteUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("User is not found"));
        userRepo.delete(user);
    }


}
