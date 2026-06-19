package com.example.schooldairy.dto;

import com.example.schooldairy.entity.SubjectMarks;
import lombok.Data;

import java.util.List;

@Data
public class ReportCardDTO {

    private Long studentId;

    private String studentName;

    private Integer studentClass;

    private String section;

    private String examName;

    private List<SubjectMarks> subjects;

    private Integer totalMarksObtained;

    private Integer totalMaxMarks;

    private Double percentage;

    private Double attendancePercentage;

    private String finalGrade;

    private String remarks;
}