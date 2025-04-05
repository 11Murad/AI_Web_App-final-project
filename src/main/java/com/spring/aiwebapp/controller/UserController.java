package com.spring.aiwebapp.controller;
import com.spring.aiwebapp.DTO.request.UserRequest;
import com.spring.aiwebapp.DTO.response.Result;
import com.spring.aiwebapp.DTO.response.UserResponse;
import com.spring.aiwebapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void create(@RequestBody UserRequest userRequest) {
         userService.createUser(userRequest);
    }

    @GetMapping("/search/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Result<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

