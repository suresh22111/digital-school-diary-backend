package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService service;

    // Add student API
    @PostMapping("/add")
    public Student addStudent(
            @RequestBody Student student
    ) {

        return service.addStudent(student);
    }

    // Get all students API
    @GetMapping
    public List<Student> getAllStudents() {

        return service.getAllStudents();
    }
// delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @PathVariable Long id
    ) {
        service.deleteStudent(id);
    }

    // Search by Student ID

    @GetMapping("/student-id/{studentId}")
    public Student getStudentByStudentId(
            @PathVariable Long studentId
    ) {

        return service.getStudentByStudentId(
                studentId
        );
    }


// Update Student

    @PutMapping("/{id}")
    public Student updateStudent(

            @PathVariable Long id,

            @RequestBody Student student

    ) {

        return service.updateStudent(
                id,
                student
        );
    }

    @GetMapping("/class/{studentClass}/section/{section}")
    public List<Student> getStudentsByClassAndSection(

            @PathVariable int studentClass,

            @PathVariable String section
    ) {

        return service.getStudentsByClassAndSection(
                studentClass,
                section
        );
    }


}