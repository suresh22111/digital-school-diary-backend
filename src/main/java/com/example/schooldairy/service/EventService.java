package com.example.schooldairy.service;

import com.example.schooldairy.entity.Event;

import com.example.schooldairy.entity.NotificationType;
import com.example.schooldairy.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NotificationService notificationService;

    // Add Event
    public Event addEvent(
            Event event
    ) {

        event.setExpiryDate(
                LocalDate.now()
                        .plusDays(2)
                        .toString()
        );

        Event savedEvent =
                repository.save(event);

// Global Announcement
        if (Boolean.TRUE.equals(event.getIsGlobal())) {

            notificationService.notifyAllStudents(

                    "New Event",

                    event.getTitle(),

                    NotificationType.EVENT

            );

        }
// Class Announcement
        else {

            notificationService.notifyClassStudents(

                    event.getStudentClass(),

                    event.getSection(),

                    "New Event",

                    event.getTitle(),

                    NotificationType.EVENT

            );

        }

        return savedEvent;
    }

    // Get All Events
    public List<Event> getAllEvents() {

        return repository.findAll()
                .stream()
                .filter(event ->
                        event.getExpiryDate() != null &&
                                !LocalDate.parse(
                                        event.getExpiryDate()
                                ).isBefore(
                                        LocalDate.now()
                                )
                )
                .toList();
    }

    public List<Event> getActiveEvents() {

        return repository.getActiveEvents();
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

        return result.stream()
                .filter(event ->
                        event.getExpiryDate() != null &&
                                !LocalDate.parse(
                                        event.getExpiryDate()
                                ).isBefore(
                                        LocalDate.now()
                                )
                )
                .toList();
    }


}
