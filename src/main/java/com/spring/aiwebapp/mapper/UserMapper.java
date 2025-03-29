package com.spring.aiwebapp.mapper;

import com.spring.aiwebapp.entity.User;
import com.spring.aiwebapp.model.request.UserRequest;
import com.spring.aiwebapp.model.response.UserResponse;

public interface UserMapper {
    static User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    static UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
