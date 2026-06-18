package com.example.schooldairy.dto;

public class ParentAttendanceDashboardDTO {

    private Long studentId;
    private String studentName;
    private Integer studentClass;
    private String section;

    private Long totalDays;
    private Long presentDays;
    private Long absentDays;

    private Double attendancePercentage;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Long totalDays) {
        this.totalDays = totalDays;
    }

    public Long getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(Long presentDays) {
        this.presentDays = presentDays;
    }

    public Long getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Long absentDays) {
        this.absentDays = absentDays;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

}