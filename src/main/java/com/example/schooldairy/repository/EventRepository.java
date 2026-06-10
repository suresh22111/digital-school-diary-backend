package com.example.schooldairy.repository;

import com.example.schooldairy.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository
        extends JpaRepository<Event, Long> {

    List<Event> findByIsGlobalTrue();

    List<Event> findByStudentClassAndSection(

            Integer studentClass,

            String section
    );
}
