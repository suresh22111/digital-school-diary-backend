package com.example.schooldairy.dto;

public class ParentLoginResponse {

    private String token;
    private String parentName;
    private Integer studentId;

    public ParentLoginResponse() {
    }

    public ParentLoginResponse(
            String token,
            String parentName,
            Integer studentId
    ) {
        this.token = token;
        this.parentName = parentName;
        this.studentId = studentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}