package com.example.schooldairy.service;

import com.example.schooldairy.dto.ReportCardDTO;
import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.entity.MarksSheet;
import com.example.schooldairy.repository.AttendanceRepository;
import com.example.schooldairy.repository.MarksSheetRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCardService {

    private final MarksSheetRepository marksSheetRepository;

    private final AttendanceRepository attendanceRepository;

    public ReportCardDTO generateReportCard(
            Long studentId
    ) {

        List<MarksSheet> marksList =
                marksSheetRepository
                        .findByStudentId(studentId);

        List<Attendance> attendanceList =
                attendanceRepository
                        .findByStudentId(studentId);

        if (marksList.isEmpty()) {
            throw new RuntimeException(
                    "No marks found for student"
            );
        }

        ReportCardDTO dto =
                new ReportCardDTO();

        dto.setStudentId(studentId);

        dto.setStudentName(
                marksList.get(0).getStudentName()
        );

        dto.setStudentClass(
                marksList.get(0).getStudentClass()
        );

        dto.setSection(
                marksList.get(0).getSection()
        );

        int totalObtained = 0;
        int totalMax = 0;

        for (MarksSheet mark : marksList) {

            totalObtained +=
                    mark.getMarksObtained();

            totalMax +=
                    mark.getMaxMarks();
        }

        dto.setTotalMarksObtained(
                totalObtained
        );

        dto.setTotalMaxMarks(
                totalMax
        );

        double percentage =
                ((double) totalObtained /
                        totalMax) * 100;

        dto.setPercentage(
                Math.round(
                        percentage * 100.0
                ) / 100.0
        );

        long presentCount =
                attendanceList.stream()
                        .filter(a ->
                                "PRESENT"
                                        .equalsIgnoreCase(
                                                a.getStatus()
                                        )
                        )
                        .count();

        double attendancePercentage =
                attendanceList.isEmpty()
                        ? 0
                        : ((double) presentCount
                        / attendanceList.size())
                        * 100;

        dto.setAttendancePercentage(
                Math.round(
                        attendancePercentage
                                * 100.0
                ) / 100.0
        );

        // Grade

        if (percentage >= 90) {

            dto.setFinalGrade("A+");

            dto.setRemarks(
                    "Excellent Performance"
            );

        } else if (percentage >= 75) {

            dto.setFinalGrade("A");

            dto.setRemarks(
                    "Very Good"
            );

        } else if (percentage >= 60) {

            dto.setFinalGrade("B");

            dto.setRemarks(
                    "Good"
            );

        } else if (percentage >= 40) {

            dto.setFinalGrade("C");

            dto.setRemarks(
                    "Needs Improvement"
            );

        } else {

            dto.setFinalGrade("D");

            dto.setRemarks(
                    "Fail"
            );
        }

        return dto;
    }
}