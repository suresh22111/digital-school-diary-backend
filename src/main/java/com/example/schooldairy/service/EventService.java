package com.example.schooldairy.service;

import com.example.schooldairy.entity.Event;

import com.example.schooldairy.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    // Add Event
    public Event addEvent(
            Event event
    ) {

        return repository.save(event);
    }

    // Get All Events
    public List<Event> getAllEvents() {

        return repository.findAll();
    }

    public List<Event> getEvents(

            Integer studentClass,

            String section
    ) {

        List<Event> result =
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

        return result;
    }
}
