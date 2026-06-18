package com.example.schooldairy.service;

import com.example.schooldairy.entity.Attendance;
import com.example.schooldairy.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.schooldairy.dto.ParentAttendanceDashboardDTO;
import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;
import com.example.schooldairy.dto.AttendanceAnalyticsDTO;
import com.example.schooldairy.dto.LowAttendanceDTO;
import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;

import java.util.ArrayList;
import com.example.schooldairy.dto.AttendanceRankingDTO;
import com.example.schooldairy.dto.MonthlyAttendanceTrendDTO;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
@Service
public class AttendanceService {

    @Autowired
    private StudentRepository studentRepository;
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

    public ParentAttendanceDashboardDTO getParentDashboard(
            Long studentId
    ) {

        Student student =
                studentRepository
                        .findByStudentId(studentId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Student Not Found"
                                )
                        );

        List<Attendance> attendanceList =
                repository.findByStudentId(studentId);

        long totalDays =
                attendanceList.size();

        long presentDays =
                attendanceList.stream()
                        .filter(a ->
                                "PRESENT".equalsIgnoreCase(
                                        a.getStatus()
                                )
                        )
                        .count();

        long absentDays =
                attendanceList.stream()
                        .filter(a ->
                                "ABSENT".equalsIgnoreCase(
                                        a.getStatus()
                                )
                        )
                        .count();

        double attendancePercentage =
                totalDays == 0
                        ? 0
                        : ((double) presentDays / totalDays) * 100;

        ParentAttendanceDashboardDTO dto =
                new ParentAttendanceDashboardDTO();

        dto.setStudentId(
                student.getStudentId()
        );

        dto.setStudentName(
                student.getName()
        );

        dto.setStudentClass(
                student.getStudentClass()
        );

        dto.setSection(
                student.getSection()
        );

        dto.setTotalDays(
                totalDays
        );

        dto.setPresentDays(
                presentDays
        );

        dto.setAbsentDays(
                absentDays
        );

        dto.setAttendancePercentage(
                attendancePercentage
        );

        return dto;
    }
    public AttendanceAnalyticsDTO getAnalytics(
            Integer studentClass,
            String section,
            String attendanceDate
    ) {

        List<Attendance> attendanceList =
                repository
                        .findByStudentClassAndSectionAndAttendanceDate(
                                studentClass,
                                section,
                                attendanceDate
                        );

        long totalStudents =
                attendanceList.size();

        long presentCount =
                attendanceList.stream()
                        .filter(a ->
                                "PRESENT".equalsIgnoreCase(
                                        a.getStatus()
                                ))
                        .count();

        long absentCount =
                attendanceList.stream()
                        .filter(a ->
                                "ABSENT".equalsIgnoreCase(
                                        a.getStatus()
                                ))
                        .count();

        long leaveCount =
                attendanceList.stream()
                        .filter(a ->
                                "LEAVE".equalsIgnoreCase(
                                        a.getStatus()
                                ))
                        .count();

        double attendancePercentage =
                totalStudents == 0
                        ? 0
                        : ((double) presentCount
                        / totalStudents) * 100;

        AttendanceAnalyticsDTO dto =
                new AttendanceAnalyticsDTO();

        dto.setTotalStudents(
                totalStudents
        );

        dto.setPresentCount(
                presentCount
        );

        dto.setAbsentCount(
                absentCount
        );

        dto.setLeaveCount(
                leaveCount
        );

        dto.setAttendancePercentage(
                attendancePercentage
        );

        return dto;
    }

    public List<LowAttendanceDTO> getLowAttendanceStudents(
            double threshold
    ) {

        List<Student> students =
                studentRepository.findAll();

        List<LowAttendanceDTO> result =
                new ArrayList<>();

        for (Student student : students) {

            List<Attendance> attendanceList =
                    repository.findByStudentId(
                            student.getStudentId()
                    );

            long totalDays =
                    attendanceList.size();

            long presentDays =
                    attendanceList.stream()
                            .filter(a ->
                                    "PRESENT".equalsIgnoreCase(
                                            a.getStatus()
                                    )
                            )
                            .count();

            double percentage =
                    totalDays > 0
                            ? ((double) presentDays / totalDays) * 100
                            : 0;

            if (percentage < threshold) {

                result.add(
                        new LowAttendanceDTO(
                                student.getStudentId(),
                                student.getName(),
                                student.getStudentClass(),
                                student.getSection(),
                                totalDays,
                                presentDays,
                                percentage
                        )
                );
            }
        }

        return result;
    }

    public List<AttendanceRankingDTO> getAttendanceRanking(
            Integer studentClass,
            String section
    ) {

        List<Student> students =
                studentRepository
                        .findByStudentClassAndSection(
                                studentClass,
                                section
                        );

        List<AttendanceRankingDTO> ranking =
                new ArrayList<>();

        for (Student student : students) {

            List<Attendance> attendanceList =
                    repository.findByStudentId(
                            student.getStudentId()
                    );

            long totalDays =
                    attendanceList.size();

            long presentDays =
                    attendanceList.stream()
                            .filter(a ->
                                    "PRESENT".equalsIgnoreCase(
                                            a.getStatus()
                                    )
                            )
                            .count();

            double percentage =
                    totalDays == 0
                            ? 0
                            : ((double) presentDays / totalDays) * 100;

            ranking.add(
                    new AttendanceRankingDTO(
                            student.getStudentId(),
                            student.getName(),
                            student.getStudentClass(),
                            student.getSection(),
                            percentage
                    )
            );
        }

        ranking.sort(
                (a, b) ->
                        Double.compare(
                                b.getAttendancePercentage(),
                                a.getAttendancePercentage()
                        )
        );

        return ranking;
    }

    public List<MonthlyAttendanceTrendDTO>
    getMonthlyAttendanceTrend(

            Integer studentClass,

            String section
    ) {

        List<Attendance> attendanceList =
                repository.findByStudentClassAndSection(
                        studentClass,
                        section
                );

        Map<String, Integer> totalMap =
                new HashMap<>();

        Map<String, Integer> presentMap =
                new HashMap<>();

        for (Attendance attendance : attendanceList) {

            LocalDate date =
                    LocalDate.parse(
                            attendance.getAttendanceDate()
                    );

            String month =
                    date.getMonth()
                            .getDisplayName(
                                    TextStyle.SHORT,
                                    Locale.ENGLISH
                            );

            totalMap.put(
                    month,
                    totalMap.getOrDefault(
                            month,
                            0
                    ) + 1
            );

            if ("PRESENT".equalsIgnoreCase(
                    attendance.getStatus()
            )) {

                presentMap.put(
                        month,
                        presentMap.getOrDefault(
                                month,
                                0
                        ) + 1
                );
            }
        }

        List<MonthlyAttendanceTrendDTO>
                result =
                new ArrayList<>();

        for (String month :
                totalMap.keySet()) {

            int total =
                    totalMap.get(month);

            int present =
                    presentMap.getOrDefault(
                            month,
                            0
                    );

            double percentage =
                    ((double) present
                            / total) * 100;

            result.add(
                    new MonthlyAttendanceTrendDTO(
                            month,
                            percentage
                    )
            );
        }

        return result;
    }
}
