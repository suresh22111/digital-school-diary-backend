package com.example.schooldairy.service;

import com.example.schooldairy.entity.Teacher;
import com.example.schooldairy.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Add teacher
    public Teacher addTeacher(
            Teacher teacher
    ) {

        // Encrypt password before saving
        String encodedPassword =
                passwordEncoder.encode(
                        teacher.getPassword()
                );

        teacher.setPassword(encodedPassword);

        return repository.save(teacher);
    }
}