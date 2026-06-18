package com.example.schooldairy.dto;

public class AttendanceAnalyticsDTO {

    private long totalStudents;
    private long presentCount;
    private long absentCount;
    private long leaveCount;

    private double attendancePercentage;

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(long presentCount) {
        this.presentCount = presentCount;
    }

    public long getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(long absentCount) {
        this.absentCount = absentCount;
    }

    public long getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(long leaveCount) {
        this.leaveCount = leaveCount;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(
            double attendancePercentage
    ) {
        this.attendancePercentage =
                attendancePercentage;
    }
}