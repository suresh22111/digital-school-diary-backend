package com.example.schooldairy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String title;

    private String message;

    // CLASS-WISE
    private Integer studentClass;

    private String section;

    // GLOBAL ANNOUNCEMENT
    private Boolean isGlobal;

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title
    ) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message
    ) {
        this.message = message;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(
            Integer studentClass
    ) {
        this.studentClass =
                studentClass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(
            String section
    ) {
        this.section = section;
    }

    public Boolean getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(
            Boolean isGlobal
    ) {
        this.isGlobal = isGlobal;
    }
}