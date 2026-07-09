package com.example.schooldairy.service;

import com.example.schooldairy.entity.Notification;
import com.example.schooldairy.entity.NotificationType;
import com.example.schooldairy.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    // ==========================
    // CREATE NOTIFICATION
    // ==========================
    public Notification createNotification(

            Long studentId,

            String title,

            String message,

            NotificationType type
    ) {

        Notification notification =
                new Notification();

        notification.setStudentId(studentId);

        notification.setTitle(title);

        notification.setMessage(message);

        notification.setType(type);

        notification.setIsRead(false);

        notification.setCreatedAt(
                LocalDateTime.now()
        );

        return repository.save(
                notification
        );
    }

    // ==========================
// NOTIFY CLASS STUDENTS
// ==========================
    public void notifyClassStudents(

            Integer studentClass,

            String section,

            String title,

            String message,

            NotificationType type

    ) {

        List<Student> students =
                studentRepository.findByStudentClassAndSection(

                        studentClass,

                        section

                );

        for (Student student : students) {

            createNotification(

                    student.getStudentId(),

                    title,

                    message,

                    type

            );

        }

    }

    // ==========================
// NOTIFY ALL STUDENTS
// ==========================
    public void notifyAllStudents(

            String title,

            String message,

            NotificationType type

    ) {

        List<Student> students =
                studentRepository.findAll();

        for (Student student : students) {

            createNotification(

                    student.getStudentId(),

                    title,

                    message,

                    type

            );

        }

    }

    // ==========================
    // HOMEWORK
    // ==========================
    public void createHomeworkNotification(Long studentId, String subject) {

        createNotification(

                studentId,

                "Homework Uploaded",

                "New homework has been uploaded for " + subject,

                NotificationType.HOMEWORK
        );
    }

    // ==========================
    // ANNOUNCEMENT
    // ==========================
    public void createAnnouncementNotification(Long studentId, String title) {

        createNotification(

                studentId,

                "New Announcement",

                title,

                NotificationType.ANNOUNCEMENT
        );
    }

    // ==========================
    // EVENT
    // ==========================
    public void createEventNotification(Long studentId, String eventTitle) {

        createNotification(

                studentId,

                "New Event",

                eventTitle,

                NotificationType.EVENT
        );
    }

    // ==========================
    // EXAM
    // ==========================
    public void createExamNotification(Long studentId, String examName) {

        createNotification(

                studentId,

                "Exam Results Published",

                examName + " marks have been published.",

                NotificationType.EXAM
        );
    }

    // ==========================
    // GET ALL NOTIFICATIONS
    // ==========================
    public List<Notification> getStudentNotifications(

            Long studentId

    ) {

        return repository
                .findByStudentIdOrderByCreatedAtDesc(
                        studentId
                );

    }

    // ==========================
    // GET UNREAD
    // ==========================
    public List<Notification> getUnreadNotifications(

            Long studentId

    ) {

        return repository
                .findByStudentIdAndIsReadFalseOrderByCreatedAtDesc(
                        studentId
                );

    }

    // ==========================
    // UNREAD COUNT
    // ==========================
    public long getUnreadCount(

            Long studentId

    ) {

        return repository
                .countByStudentIdAndIsReadFalse(
                        studentId
                );

    }

    // ==========================
    // MARK AS READ
    // ==========================
    public Notification markAsRead(

            Long id

    ) {

        Notification notification =
                repository.findById(id)
                        .orElseThrow();

        notification.setIsRead(true);

        return repository.save(
                notification
        );

    }

}