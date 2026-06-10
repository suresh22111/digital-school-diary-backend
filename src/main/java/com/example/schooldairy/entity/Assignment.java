package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    private Integer studentClass;

    private String section;

    private String subject;

    private String dueDate;

    private String assignedBy;
}
