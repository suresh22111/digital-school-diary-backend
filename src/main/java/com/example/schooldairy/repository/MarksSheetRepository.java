package com.example.schooldairy.repository;

import com.example.schooldairy.entity.MarksSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksSheetRepository
        extends JpaRepository<MarksSheet, Long> {

    List<MarksSheet> findByStudentId(Long studentId);

    List<MarksSheet> findByStudentClassAndSection(
            Integer studentClass,
            String section
    );

    List<MarksSheet> findByStudentClassAndSectionAndExamName(
            Integer studentClass,
            String section,
            String examName
    );

}