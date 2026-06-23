package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository
        extends JpaRepository<Event, Long> {

    List<Event> findByIsGlobalTrue();

    List<Event> findByStudentClassAndSection(
            Integer studentClass,
            String section
    );

    @Query(
            value =
                    "SELECT * FROM events " +
                            "WHERE STR_TO_DATE(event_date,'%Y-%m-%d') >= CURDATE()",
            nativeQuery = true
    )
    List<Event> getActiveEvents();
}
