package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    List<Student> findByStudentClassAndSection(
            int studentClass,
            String section
    );

    Optional<Student> findByStudentId(Long studentId);
}