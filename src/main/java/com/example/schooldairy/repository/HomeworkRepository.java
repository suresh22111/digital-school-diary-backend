package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Homework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HomeworkRepository
        extends JpaRepository<Homework, Long> {

    List<Homework> findByStudentClassAndSection(
            String studentClass,
            String section
    );

    @Query("""
SELECT h
FROM Homework h
WHERE h.studentClass = ?1
AND h.section = ?2
AND h.expiryDate >= CURRENT_DATE
""")
    List<Homework> getActiveHomeworkByClassAndSection(
            String studentClass,
            String section
    );
}

