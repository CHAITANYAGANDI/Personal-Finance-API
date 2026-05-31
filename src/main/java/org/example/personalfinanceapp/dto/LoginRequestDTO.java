package org.example.personalfinanceapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 150, message = "Email must be at most 150 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password must be at least 8 characters")
    @Size(max = 100, message = "Password must be at most 100 characters")
    private String password;

    public LoginRequestDTO(){

    }

    public LoginRequestDTO(String email, String password){

        this.email = email;
        this.password = password;
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
}
