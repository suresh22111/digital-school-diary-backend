package com.example.schooldairy.service;

import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    public Attendance addAttendance(
            Attendance attendance
    ) {

        return repository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {

        return repository.findAll();
    }

    public List<Attendance> getAttendanceByClassSection(

            Integer studentClass,

            String section
    ) {

        return repository.findByStudentClassAndSection(
                studentClass,
                section
        );
    }

    public List<Attendance> getAttendanceReport(

            Integer studentClass,

            String section,

            String fromDate,

            String toDate
    ) {

        return repository
                .findByStudentClassAndSectionAndAttendanceDateBetween(
                        studentClass,
                        section,
                        fromDate,
                        toDate
                );
    }
    public List<Attendance> getAttendanceByStudentId(
            Long studentId
    ) {

        return repository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceReportByStudentId(
            Long studentId,
            String fromDate,
            String toDate
    ) {

        return repository.findByStudentIdAndAttendanceDateBetween(
                studentId,
                fromDate,
                toDate
        );
    }
}
