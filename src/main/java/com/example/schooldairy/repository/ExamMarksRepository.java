package com.example.schooldairy.repository;

import com.example.schooldairy.entity.ExamMarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamMarksRepository
        extends JpaRepository<ExamMarks, Long> {


    List<ExamMarks> findByStudentId(
            Long studentId
    );


    List<ExamMarks> findByStudentIdAndExamName(
            Long studentId,
            String examName
    );


    List<ExamMarks> findByStudentClassAndSectionAndExamName(
            Integer studentClass,
            String section,
            String examName
    );



    Optional<ExamMarks> findFirstByStudentIdAndExamName(
            Long studentId,
            String examName
    );

}