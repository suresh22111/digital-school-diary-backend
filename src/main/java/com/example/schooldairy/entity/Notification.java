package com.example.schooldairy.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student who receives this notification
    private Long studentId;

    // Notification Title
    private String title;

    // Notification Message
    @Column(length = 1000)
    private String message;

    // HOMEWORK, ANNOUNCEMENT, EVENT, EXAM
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    // Read / Unread
    private Boolean isRead = false;

    // Date & Time
    private LocalDateTime createdAt;

}