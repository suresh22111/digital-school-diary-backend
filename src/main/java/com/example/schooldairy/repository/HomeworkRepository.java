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

    @Query(
            value =
                    "SELECT * FROM homework " +
                            "WHERE student_class = ?1 " +
                            "AND section = ?2 " +
                            "AND STR_TO_DATE(upload_date,'%Y-%m-%d') >= CURDATE() - INTERVAL 7 DAY",
            nativeQuery = true
    )
    List<Homework> getActiveHomeworkByClassAndSection(
            String studentClass,
            String section
    );
}

