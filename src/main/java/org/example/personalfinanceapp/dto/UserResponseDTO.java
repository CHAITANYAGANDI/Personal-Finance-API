package org.example.personalfinanceapp.dto;

import org.example.personalfinanceapp.enums.Role;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Long id;

    private String fullName;

    private String email;

    private Role role;

    private boolean enabled;

    private boolean accountLocked;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public UserResponseDTO(Long id,
                           String fullName,
                           String email,
                           Role role,
                           boolean enabled,
                           boolean accountLocked,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt){

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
