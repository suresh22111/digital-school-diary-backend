package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marks_sheet")
public class MarksSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private String studentName;

    private Integer studentClass;

    private String section;

    private String examName;

    private String subject;

    private Integer marksObtained;

    private Integer maxMarks;

    private String grade;

    private String remarks;
}
