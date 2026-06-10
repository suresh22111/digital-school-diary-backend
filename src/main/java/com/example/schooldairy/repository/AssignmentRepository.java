package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository
        extends JpaRepository<Assignment, Long> {

    List<Assignment> findByStudentClassAndSection(

            Integer studentClass,

            String section
    );
}
