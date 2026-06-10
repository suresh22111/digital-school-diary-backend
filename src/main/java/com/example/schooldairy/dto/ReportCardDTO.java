package com.example.schooldairy.dto;

import lombok.Data;

@Data
public class ReportCardDTO {

    private Long studentId;

    private String studentName;

    private Integer studentClass;

    private String section;

    private Integer totalMarksObtained;

    private Integer totalMaxMarks;

    private Double percentage;

    private Double attendancePercentage;

    private String finalGrade;

    private String remarks;
}
