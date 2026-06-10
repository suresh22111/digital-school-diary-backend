package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.repository.AttendanceRepository;
import com.example.schooldairy.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping("/add")
    public Attendance addAttendance(
            @RequestBody Attendance attendance
    ) {

        return service.addAttendance(attendance);
    }

    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {

        return service.getAllAttendance();
    }

    @GetMapping("/{studentClass}/{section}")
    public List<Attendance> getAttendanceByClassSection(

            @PathVariable Integer studentClass,

            @PathVariable String section
    ) {

        return service.getAttendanceByClassSection(
                studentClass,
                section
        );
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudentId(
            @PathVariable Long studentId
    ) {

        return attendanceRepository
                .findByStudentId(studentId);
    }

    @GetMapping("/report")
    public List<Attendance> getAttendanceReport(

            @RequestParam Integer studentClass,

            @RequestParam String section,

            @RequestParam String fromDate,

            @RequestParam String toDate
    ) {

        return service.getAttendanceReport(
                studentClass,
                section,
                fromDate,
                toDate
        );
    }
}
