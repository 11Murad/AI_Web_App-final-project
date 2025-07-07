package com.spring.aiwebapp.mapper;
import com.spring.aiwebapp.DTO.request.UserRequestForRegister;
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
    static User toEntity(UserRequestForRegister userRequestForRegister) {
        return User.builder()
                .firstName(userRequestForRegister.getFirstName())
                .lastName(userRequestForRegister.getLastName())
                .email(userRequestForRegister.getEmail())
                .password(userRequestForRegister.getPassword())
                .build();
    }

}
