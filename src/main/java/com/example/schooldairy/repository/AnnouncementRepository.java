package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {

    // GLOBAL ANNOUNCEMENTS
    List<Announcement>
    findByIsGlobalTrue();

    // CLASS + SECTION ANNOUNCEMENTS
    List<Announcement>
    findByStudentClassAndSection(

            Integer studentClass,

            String section
    );
}