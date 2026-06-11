package org.example.personalfinanceapp.controller;

import jakarta.validation.Valid;
import org.example.personalfinanceapp.dto.AuthResponseDTO;
import org.example.personalfinanceapp.dto.LoginRequestDTO;
import org.example.personalfinanceapp.dto.RefreshTokenRequestDTO;
import org.example.personalfinanceapp.dto.RegisterRequestDTO;
import org.example.personalfinanceapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){

        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){

        AuthResponseDTO registerResponse = authService.register(registerRequestDTO);


        return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        AuthResponseDTO loginResponse = authService.login(loginRequestDTO);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){


        AuthResponseDTO refreshTokenResponse = authService.refreshToken(refreshTokenRequestDTO);

        return ResponseEntity.ok(refreshTokenResponse);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){

        authService.logout(refreshTokenRequestDTO.getRefreshToken());

        return ResponseEntity.noContent().build();
    }
}
