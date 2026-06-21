package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Homework;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository
        extends JpaRepository<Homework, Long> {

    List<Homework> findByStudentClassAndSection(
            String studentClass,
            String section
    );
}

