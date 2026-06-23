package com.example.schooldairy.service;

import com.example.schooldairy.entity.Announcement;

import com.example.schooldairy.repository.AnnouncementRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    // ADD ANNOUNCEMENT
    public Announcement addAnnouncement(
            Announcement announcement
    ) {

        announcement.setExpiryDate(
                LocalDate.now()
                        .plusDays(2)
                        .toString()
        );

        return repository.save(
                announcement
        );
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