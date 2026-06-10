package com.example.schooldairy.dto;

import com.example.schooldairy.entity.Role;

public class AuthResponse {

    private String token;

    private Role role;

    private String username;

    // CONSTRUCTOR
    public AuthResponse(

            String token,

            Role role,

            String username
    ) {

        this.token = token;

        this.role = role;

        this.username = username;
    }

    // GETTERS

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    // SETTERS

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}