package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.*;
import org.example.personalfinanceapp.entity.RefreshToken;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.enums.Role;
import org.example.personalfinanceapp.repository.RefreshTokenRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.example.personalfinanceapp.security.CustomUserDetailsService;
import org.example.personalfinanceapp.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       CustomUserDetailsService customUserDetailsService,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       AuthenticationManager authenticationManager,
                       RefreshTokenRepository refreshTokenRepository){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    private UserResponseDTO convertToUserResponseDTO(User user){

        return  new UserResponseDTO(

                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.isEnabled(),
                user.isAccountLocked(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO){

        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+ loginRequestDTO.getEmail()));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

        String accessToken = jwtService.generateAccessToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        UserResponseDTO userResponseDTO = convertToUserResponseDTO(user);

        return  new AuthResponseDTO(

                accessToken,
                refreshToken.getToken(),
                "Bearer",
                userResponseDTO
        );

    }

    @Transactional
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO){

        RefreshToken refreshToken = refreshTokenService.
                findByToken(refreshTokenRequestDTO.getRefreshToken()).
                orElseThrow(()-> new IllegalArgumentException("Refresh token not found"));

        RefreshToken verifiedToken = refreshTokenService.verifyExpiration(refreshToken);

        User user = verifiedToken.getUser();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

        String accessToken = jwtService.generateAccessToken(userDetails);

        UserResponseDTO userResponseDTO = convertToUserResponseDTO(user);

        return new AuthResponseDTO(
                accessToken,
                verifiedToken.getToken(),
                "Bearer",
                userResponseDTO
        );

    }

    @Transactional
    public void logout(String refreshToken){

        refreshTokenService.findByToken(refreshToken)
                .ifPresent(token -> refreshTokenService.deleteByUserId(token.getUser().getId()));

    }

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO requestDTO){

        if(userRepository.existsByEmail(requestDTO.getEmail())){

            throw new IllegalArgumentException("Email is already registered");
        }

        String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());

        User user = new User(
                requestDTO.getFullName(),
                requestDTO.getEmail(),
                encodedPassword,
                Role.USER
        );

        User savedUser = userRepository.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(savedUser.getEmail());

        String accessToken = jwtService.generateAccessToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser.getId());

        UserResponseDTO userResponseDTO = convertToUserResponseDTO(savedUser);

        return new AuthResponseDTO(

                accessToken,
                refreshToken.getToken(),
                "Bearer",
                userResponseDTO
        );
    }
}
