package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Homework;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository
        extends JpaRepository<Homework, Long> {
}