package org.example.personalfinanceapp.entity;

import jakarta.persistence.*;
import org.example.personalfinanceapp.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 150 )
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountLocked = false;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(){


    }

    public User(String fullName, String email, String password, Role role){

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public void setId(Long id){

        this.id = id;
    }

    public Long getId(){

        return id;
    }

    public void setFullName(String fullName){

        this.fullName = fullName;
    }

    public String getFullName(){

        return fullName;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public String getEmail(){

        return email;

    }

    public void setPassword(String password){

        this.password = password;
    }

    public String getPassword(){

        return password;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){

        return role;
    }

    public void setEnabled(boolean enabled){

        this.enabled = enabled;
    }

    public boolean isEnabled(){

        return enabled;
    }

    public void setAccountLocked(boolean accountLocked){

        this.accountLocked = accountLocked;
    }

    public boolean isAccountLocked(){

        return accountLocked;
    }

    public LocalDateTime getCreatedAt(){

        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){

        return updatedAt;
    }
}
