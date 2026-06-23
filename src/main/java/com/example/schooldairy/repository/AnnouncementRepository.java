package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

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

    @Query(
            value =
                    "SELECT * FROM announcements " +
                            "WHERE STR_TO_DATE(expiry_date,'%Y-%m-%d') >= CURDATE()",
            nativeQuery = true
    )
    List<Announcement>
    getActiveAnnouncements();
}