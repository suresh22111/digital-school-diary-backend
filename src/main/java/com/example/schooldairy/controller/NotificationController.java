package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Notification;
import com.example.schooldairy.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService service;

    // ==========================
    // GET ALL NOTIFICATIONS
    // ==========================
    @GetMapping("/student/{studentId}")
    public List<Notification> getStudentNotifications(

            @PathVariable Long studentId

    ) {

        return service.getStudentNotifications(
                studentId
        );

    }

    // ==========================
    // GET UNREAD NOTIFICATIONS
    // ==========================
    @GetMapping("/unread/{studentId}")
    public List<Notification> getUnreadNotifications(

            @PathVariable Long studentId

    ) {

        return service.getUnreadNotifications(
                studentId
        );

    }

    // ==========================
    // GET UNREAD COUNT
    // ==========================
    @GetMapping("/count/{studentId}")
    public long getUnreadCount(

            @PathVariable Long studentId

    ) {

        return service.getUnreadCount(
                studentId
        );

    }

    // ==========================
    // MARK AS READ
    // ==========================
    @PutMapping("/read/{id}")
    public Notification markAsRead(

            @PathVariable Long id

    ) {

        return service.markAsRead(
                id
        );

    }

}