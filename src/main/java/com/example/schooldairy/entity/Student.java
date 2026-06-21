package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private String name;

    private Integer studentClass;

    private String section;

    private String fatherName;

    private String motherName;

    private String parentMobile;

    private String email;

    private String gender;

    private LocalDate dateOfBirth;

    private String address;

    private String photoUrl;
}