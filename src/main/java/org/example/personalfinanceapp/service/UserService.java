package org.example.personalfinanceapp.service;

import org.example.personalfinanceapp.dto.UserResponseDTO;
import org.example.personalfinanceapp.entity.User;
import org.example.personalfinanceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    private UserResponseDTO convertToResponseDTO(User user){

        return new UserResponseDTO(
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

    @Transactional(readOnly = true)
    public UserResponseDTO getCurrentUserProfile(String email){

        User user =  userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("user not found with email: "+ email));


        return convertToResponseDTO(user);

    }
}
