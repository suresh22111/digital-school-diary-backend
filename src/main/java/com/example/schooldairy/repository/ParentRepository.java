package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository
        extends JpaRepository<Parent, Long> {

    // Find parent by mobile number
    Optional<Parent> findByMobile(
            String mobile
    );

    // Find parent by student id
    Optional<Parent> findByStudentId(
            Integer studentId
    );

    // Check mobile already exists
    boolean existsByMobile(
            String mobile
    );
}