package com.example.schooldairy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceRankingDTO {

    private Long studentId;
    private String studentName;
    private Integer studentClass;
    private String section;

    private double attendancePercentage;
}
