package com.example.schooldairy.service;

import com.example.schooldairy.entity.Announcement;

import com.example.schooldairy.entity.NotificationType;
import com.example.schooldairy.repository.AnnouncementRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;
@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NotificationService notificationService;

    // ADD ANNOUNCEMENT
    public Announcement addAnnouncement(
            Announcement announcement
    ) {

        announcement.setExpiryDate(
                LocalDate.now()
                        .plusDays(2)
                        .toString()
        );

        Announcement savedAnnouncement =
                repository.save(announcement);

// Global Announcement
        if (Boolean.TRUE.equals(announcement.getIsGlobal())) {

            List<Student> students =
                    studentRepository.findAll();

            for (Student student : students) {

                notificationService.createAnnouncementNotification(

                        student.getStudentId(),

                        announcement.getTitle()

                );

            }

        }
// Global Announcement
        if (Boolean.TRUE.equals(announcement.getIsGlobal())) {

            notificationService.notifyAllStudents(

                    "New Announcement",

                    announcement.getTitle(),

                    NotificationType.ANNOUNCEMENT

            );

        }
// Class Announcement
        else {

            notificationService.notifyClassStudents(

                    announcement.getStudentClass(),

                    announcement.getSection(),

                    "New Announcement",

                    announcement.getTitle(),

                    NotificationType.ANNOUNCEMENT

            );

        }

        return savedAnnouncement;
    }

    // GET GLOBAL + CLASS ANNOUNCEMENTS
    public List<Announcement> getAnnouncements(

            Integer studentClass,

            String section
    ) {

        List<Announcement> result =
                new ArrayList<>();

        result.addAll(
                repository.findByIsGlobalTrue()
        );

        result.addAll(
                repository.findByStudentClassAndSection(
                        studentClass,
                        section
                )
        );

        System.out.println("TODAY = " + LocalDate.now());

        result.forEach(a ->
                System.out.println(
                        a.getTitle() +
                                " -> " +
                                a.getExpiryDate()
                )
        );

        return result.stream()
                .filter(a ->
                        a.getExpiryDate() != null &&
                                !LocalDate.parse(
                                        a.getExpiryDate()
                                ).isBefore(
                                        LocalDate.now()
                                )
                )
                .toList();
    }
    // GET ALL ANNOUNCEMENTS
    public List<Announcement>
    getAllAnnouncements() {

        return repository.findAll()
                .stream()
                .filter(a ->
                        a.getExpiryDate() != null &&
                                !LocalDate.parse(
                                        a.getExpiryDate()
                                ).isBefore(
                                        LocalDate.now()
                                )
                )
                .toList();
    }

    public List<Announcement> getActiveAnnouncements() {

        return repository.getActiveAnnouncements();
    }
}