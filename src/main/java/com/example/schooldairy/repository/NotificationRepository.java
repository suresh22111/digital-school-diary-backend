package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    // Get all notifications of a student
    List<Notification> findByStudentIdOrderByCreatedAtDesc(
            Long studentId
    );

    // Get unread notifications
    List<Notification> findByStudentIdAndIsReadFalseOrderByCreatedAtDesc(
            Long studentId
    );

    // Count unread notifications
    long countByStudentIdAndIsReadFalse(
            Long studentId
    );

}