package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.entity.RefreshToken;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.repository.RefreshTokenRepository;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Value("${jwt.refresh-token-expiration-ms}")
    private long refreshTokenExpirationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository){

        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public RefreshToken createRefreshToken(Long userId){


        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with id: " + userId));

        refreshTokenRepository.deleteByUserId(user.getId());

        String token = UUID.randomUUID().toString();

        Instant expiryDate = Instant.now().plusMillis(refreshTokenExpirationMs);

        RefreshToken refreshToken = new RefreshToken(token,expiryDate,user);

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token){

        return refreshTokenRepository.findByToken(token);
    }


    @Transactional
    public  RefreshToken verifyExpiration(RefreshToken token){

        if(token.isRevoked()){

            throw new IllegalArgumentException("Refresh token is revoked");
        }

        if(token.getExpiryDate().isBefore(Instant.now())){

            refreshTokenRepository.deleteByUserId(token.getUser().getId());

            throw new IllegalArgumentException("Refresh token is expired");
        }


        return token;
    }

    @Transactional
    public void deleteByUserId(Long userId){

        refreshTokenRepository.deleteByUserId(userId);
    }
}
