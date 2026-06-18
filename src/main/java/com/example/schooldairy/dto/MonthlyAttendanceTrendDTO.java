package com.example.schooldairy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyAttendanceTrendDTO {

    private String month;

    private double attendancePercentage;
}