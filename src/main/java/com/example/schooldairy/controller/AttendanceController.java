package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.repository.AttendanceRepository;
import com.example.schooldairy.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.schooldairy.dto.AttendanceAnalyticsDTO;

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

    @PutMapping("/update/{id}")
    public Attendance updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendance
    ) {

        attendance.setId(id);

        return attendanceRepository.save(attendance);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAttendance(
            @PathVariable Long id
    ) {

        attendanceRepository.deleteById(id);

        return "Attendance Deleted Successfully";
    }

    @GetMapping("/student-report")
    public List<Attendance> getStudentAttendanceReport(

            @RequestParam Long studentId,

            @RequestParam String fromDate,

            @RequestParam String toDate
    ) {

        return service.getAttendanceReportByStudentId(
                studentId,
                fromDate,
                toDate
        );
    }
   @GetMapping("/parent-dashboard/{studentId}")
    public ResponseEntity<?> getParentDashboard(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                service.getParentDashboard(studentId)
        );
    }

    @GetMapping("/analytics")
    public AttendanceAnalyticsDTO getAnalytics(

            @RequestParam Integer studentClass,

            @RequestParam String section,

            @RequestParam String attendanceDate
    ) {

        return service.getAnalytics(
                studentClass,
                section,
                attendanceDate
        );
    }

    @GetMapping("/low-attendance")
    public ResponseEntity<?> getLowAttendanceStudents(
            @RequestParam double threshold
    ) {

        return ResponseEntity.ok(
                service.getLowAttendanceStudents(
                        threshold
                )
        );
    }

    @GetMapping("/ranking")
    public ResponseEntity<?> getRanking(

            @RequestParam Integer studentClass,

            @RequestParam String section
    ) {

        return ResponseEntity.ok(
                service.getAttendanceRanking(
                        studentClass,
                        section
                )
        );
    }

    @GetMapping("/monthly-trend")
    public ResponseEntity<?> getMonthlyTrend(

            @RequestParam Integer studentClass,

            @RequestParam String section
    ) {

        return ResponseEntity.ok(
                service.getMonthlyAttendanceTrend(
                        studentClass,
                        section
                )
        );
    }


}
