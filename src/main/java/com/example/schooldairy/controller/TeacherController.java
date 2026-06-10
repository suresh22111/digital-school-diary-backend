package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Teacher;
import com.example.schooldairy.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    // Add teacher API
    @PostMapping("/add")
    public Teacher addTeacher(
            @RequestBody Teacher teacher
    ) {

        return service.addTeacher(teacher);
    }
}