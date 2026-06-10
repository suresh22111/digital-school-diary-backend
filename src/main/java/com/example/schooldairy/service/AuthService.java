package com.example.schooldairy.service;

import com.example.schooldairy.dto.AuthRequest;
import com.example.schooldairy.dto.AuthResponse;

import com.example.schooldairy.entity.Teacher;

import com.example.schooldairy.repository.TeacherRepository;

import com.example.schooldairy.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =========================
    // REGISTER
    // =========================
    public AuthResponse register(
            AuthRequest request
    ) {

        Teacher teacher = new Teacher();

        teacher.setName(
                request.getName()
        );

        teacher.setUsername(
                request.getUsername()
        );

        // ENCODE PASSWORD
        teacher.setPassword(

                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        teacher.setRole(
                request.getRole()
        );

        teacherRepository.save(
                teacher
        );

        // GENERATE TOKEN
        String token =
                jwtService.generateToken(
                        teacher.getUsername()
                );

        return new AuthResponse(

                token,

                teacher.getRole(),

                teacher.getUsername()
        );
    }

    // =========================
    // LOGIN
    // =========================
    public AuthResponse login(
            AuthRequest request
    ) {

        Teacher teacher =
                teacherRepository
                        .findByUsername(
                                request.getUsername()
                        )
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "User Not Found"
                                )
                        );

        // PASSWORD CHECK
        boolean isValid =
                passwordEncoder.matches(

                        request.getPassword(),

                        teacher.getPassword()
                );

        if (!isValid) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        // GENERATE TOKEN
        String token =
                jwtService.generateToken(
                        teacher.getUsername()
                );

        return new AuthResponse(

                token,

                teacher.getRole(),

                teacher.getUsername()
        );
    }
}