package com.example.schooldairy.dto;

public class LoginResponse {

    private String token;

    private String role;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            String role
    ) {

        this.token = token;
        this.role = role;
    }

    // GET TOKEN
    public String getToken() {
        return token;
    }

    public void setToken(
            String token
    ) {
        this.token = token;
    }

    // GET ROLE
    public String getRole() {
        return role;
    }

    public void setRole(
            String role
    ) {
        this.role = role;
    }
}