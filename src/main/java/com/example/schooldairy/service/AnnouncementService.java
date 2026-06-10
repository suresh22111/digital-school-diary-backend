package com.example.schooldairy.service;

import com.example.schooldairy.entity.Announcement;

import com.example.schooldairy.repository.AnnouncementRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    // ADD ANNOUNCEMENT
    public Announcement addAnnouncement(
            Announcement announcement
    ) {

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

        // GLOBAL
        result.addAll(

                repository
                        .findByIsGlobalTrue()
        );

        // CLASS + SECTION
        result.addAll(

                repository
                        .findByStudentClassAndSection(

                                studentClass,

                                section
                        )
        );

        return result;
    }

    // GET ALL ANNOUNCEMENTS
    public List<Announcement>
    getAllAnnouncements() {

        return repository.findAll();
    }
}