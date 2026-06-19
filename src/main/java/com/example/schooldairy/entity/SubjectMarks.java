package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marks_subject")
public class SubjectMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String subject;


    private Integer marksObtained;


    private Integer maxMarks;


    private String grade;


    private String remarks;

}