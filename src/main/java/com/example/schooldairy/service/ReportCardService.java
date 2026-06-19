package com.example.schooldairy.service;

import com.example.schooldairy.dto.ReportCardDTO;
import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.entity.ExamMarks;
import com.example.schooldairy.entity.SubjectMarks;
import com.example.schooldairy.repository.AttendanceRepository;
import com.example.schooldairy.repository.ExamMarksRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCardService {

    private final ExamMarksRepository examMarksRepository;

    private final AttendanceRepository attendanceRepository;

    public ReportCardDTO generateReportCard(
            Long studentId,
            String examName
    ) {

        ExamMarks exam =
                examMarksRepository
                        .findFirstByStudentIdAndExamName(
                                studentId,
                                examName
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "No marks found for student and exam"
                                )
                        );

        List<Attendance> attendanceList =
                attendanceRepository
                        .findByStudentId(studentId);

        ReportCardDTO dto =
                new ReportCardDTO();

        dto.setStudentId(
                exam.getStudentId()
        );

        dto.setStudentName(
                exam.getStudentName()
        );

        dto.setStudentClass(
                exam.getStudentClass()
        );

        dto.setSection(
                exam.getSection()
        );

        dto.setExamName(
                exam.getExamName()
        );

        dto.setSubjects(
                exam.getSubjects()
        );

        int totalObtained = 0;
        int totalMax = 0;

        for (
                SubjectMarks subject :
                exam.getSubjects()
        ) {

            totalObtained +=
                    subject.getMarksObtained();

            totalMax +=
                    subject.getMaxMarks();
        }

        dto.setTotalMarksObtained(
                totalObtained
        );

        dto.setTotalMaxMarks(
                totalMax
        );

        double percentage =
                totalMax == 0
                        ? 0
                        : ((double) totalObtained
                        / totalMax) * 100;

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
                        attendancePercentage * 100.0
                ) / 100.0
        );

        // Grade Calculation

        if (percentage >= 90) {

            dto.setFinalGrade("A+");
            dto.setRemarks(
                    "Excellent Performance"
            );

        } else if (percentage >= 80) {

            dto.setFinalGrade("A");
            dto.setRemarks(
                    "Very Good"
            );

        } else if (percentage >= 70) {

            dto.setFinalGrade("B+");
            dto.setRemarks(
                    "Good Performance"
            );

        } else if (percentage >= 60) {

            dto.setFinalGrade("B");
            dto.setRemarks(
                    "Good"
            );

        } else if (percentage >= 50) {

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