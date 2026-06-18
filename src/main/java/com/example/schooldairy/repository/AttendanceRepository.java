package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByStudentClassAndSection(
            Integer studentClass,
            String section
    );



    List<Attendance> findByStudentClassAndSectionAndAttendanceDateBetween(
            Integer studentClass,
            String section,
            String fromDate,
            String toDate
    );

    List<Attendance> findByStudentIdAndAttendanceDateBetween(
            Long studentId,
            String fromDate,
            String toDate
    );

    List<Attendance>
    findByStudentClassAndSectionAndAttendanceDate(

            Integer studentClass,

            String section,

            String attendanceDate
    );
}