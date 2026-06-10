package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private String studentName;

    private Integer studentClass;

    private String section;

    private String attendanceDate;

    private String status;

    private String remarks;
}
