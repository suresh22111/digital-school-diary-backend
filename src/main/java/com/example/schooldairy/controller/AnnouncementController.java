package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Announcement;

import com.example.schooldairy.service.AnnouncementService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin(origins = "*")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    // ADD ANNOUNCEMENT
    @PostMapping("/add")
    public Announcement addAnnouncement(

            @RequestBody Announcement announcement
    ) {

        return service.addAnnouncement(
                announcement
        );
    }

    // GET CLASS + GLOBAL ANNOUNCEMENTS
    @GetMapping("/{studentClass}/{section}")
    public List<Announcement> getAnnouncements(

            @PathVariable Integer studentClass,

            @PathVariable String section
    ) {

        return service.getAnnouncements(

                studentClass,

                section
        );
    }

    // GET ALL ANNOUNCEMENTS


    @GetMapping("/active")
    public List<Announcement>
    getActiveAnnouncements() {

        return service
                .getActiveAnnouncements();
    }

    // GET ALL ANNOUNCEMENTS
    @GetMapping("/all")
    public List<Announcement> getAllAnnouncements() {

        return service.getAllAnnouncements();
    }
}
