package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exam_marks")
public class ExamMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long studentId;

    private String studentName;

    private Integer studentClass;

    private String section;

    private String examName;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "exam_id"
    )
    private List<SubjectMarks> subjects;

}
