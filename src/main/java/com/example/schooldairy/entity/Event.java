package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // EVENT or HOLIDAY
    private String type;

    private String eventDate;

    private String createdAt;

    private Integer studentClass;

    private String section;

    private Boolean isGlobal;
}
