package org.example.personalfinanceapp.dto;

public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private UserResponseDTO user;

    public AuthResponseDTO(String accessToken,
                           String refreshToken,
                           String tokenType,
                           UserResponseDTO user){

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.user = user;

    }


    public String getAccessToken(){

        return accessToken;
    }

    public String getRefreshToken(){

        return refreshToken;
    }

    public String getTokenType(){

        return tokenType;
    }

    public UserResponseDTO getUser(){

        return user;
    }

}
