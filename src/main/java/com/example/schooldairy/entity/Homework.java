package com.example.schooldairy.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "student_class")
    private String studentClass;

    @Column(name = "section")
    private String section;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "uploaded_by")
    private String uploadedBy;
}
