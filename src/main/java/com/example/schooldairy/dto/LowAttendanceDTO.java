package com.example.schooldairy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowAttendanceDTO {

    private Long studentId;
    private String studentName;
    private Integer studentClass;
    private String section;

    private long totalDays;
    private long presentDays;

    private double attendancePercentage;
}
