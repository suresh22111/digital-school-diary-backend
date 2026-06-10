package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Event;

import com.example.schooldairy.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService service;

    // Add Event
    @PostMapping("/add")
    public Event addEvent(

            @RequestBody Event event
    ) {

        return service.addEvent(event);
    }

    // Get All Events
    @GetMapping("/all")
    public List<Event> getAllEvents() {

        return service.getAllEvents();
    }

    @GetMapping("/{studentClass}/{section}")
    public List<Event> getEvents(

            @PathVariable Integer studentClass,

            @PathVariable String section
    ) {

        return service.getEvents(
                studentClass,
                section
        );
    }
}
