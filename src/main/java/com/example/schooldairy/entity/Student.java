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

    // Temporary field (VERY IMPORTANT)
    private String name;

    // Student Name
    private String surname;

    private String firstName;

    private String lastName;

    private Integer studentClass;

    private String section;

    // Parent Details
    private String fatherName;

    private String motherName;

    private String parentMobile;

    private String email;

    private String gender;

    private LocalDate dateOfBirth;

    private String address;

    private String photoUrl;

    // Helper Method (Not stored in DB)
    @Transient
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();

        if (surname != null && !surname.isBlank()) {
            fullName.append(surname).append(" ");
        }

        if (firstName != null && !firstName.isBlank()) {
            fullName.append(firstName).append(" ");
        }

        if (lastName != null && !lastName.isBlank()) {
            fullName.append(lastName);
        }

        return fullName.toString().trim();
    }
}