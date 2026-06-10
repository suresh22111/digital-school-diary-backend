package com.example.schooldairy.controller;

import com.example.schooldairy.dto.AuthRequest;
import com.example.schooldairy.dto.AuthResponse;

import com.example.schooldairy.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")

@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // =========================
    // LOGIN API
    // =========================
    @PostMapping("/login")
    public AuthResponse login(

            @RequestBody
            AuthRequest request
    ) {

        return authService.login(
                request
        );
    }

    // =========================
    // REGISTER API
    // =========================
    @PostMapping("/register")
    public AuthResponse register(

            @RequestBody
            AuthRequest request
    ) {

        return authService.register(
                request
        );
    }
}