package org.example.personalfinanceapp.controller;


import org.example.personalfinanceapp.dto.UserResponseDTO;
import org.example.personalfinanceapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){

        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUserProfile(Authentication authentication){

        String email = authentication.getName();

        UserResponseDTO userProfile = userService.getCurrentUserProfile(email);

        return ResponseEntity.ok(userProfile);
    }





}
