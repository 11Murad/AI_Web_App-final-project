package com.spring.aiwebapp.mapper;
import com.spring.aiwebapp.DTO.request.UserRequest;
import com.spring.aiwebapp.DTO.response.UserDTO;
import com.spring.aiwebapp.entity.User;
import java.util.List;

public interface UserMapper {
    static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
    static User toEntity(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toUserDTO).toList();
    }
}
