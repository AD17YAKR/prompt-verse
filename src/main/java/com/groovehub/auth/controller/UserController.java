package com.groovehub.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.groovehub.auth.dto.UserDTO;
import com.groovehub.auth.model.User;
import com.groovehub.auth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({ "/signup/", "/signup" })
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.createUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping({ "/login/", "/login" })
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        UserDTO authenticatedUser = userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }
}