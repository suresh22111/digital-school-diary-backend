package com.example.schooldairy.dto;

import com.example.schooldairy.entity.Role;

public class AuthRequest {

    private String name;

    private String username;

    private String password;

    private Role role;

    // GETTERS

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}