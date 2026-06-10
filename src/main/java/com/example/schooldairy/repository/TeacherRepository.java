package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository
        extends JpaRepository<Teacher, Long> {

    // Find teacher by username
    Optional<Teacher> findByUsername(
            String username
    );
}