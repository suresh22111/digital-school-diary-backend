package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;
import com.example.schooldairy.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Cloudinary cloudinary;

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

    @GetMapping("/mobile/{mobile}")
    public Student getStudentByMobile(

            @PathVariable String mobile

    ) {

        return service.getStudentByMobile(
                mobile
        );
    }

    @PostMapping("/upload-photo")
    public String uploadPhoto(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            String url = uploadResult.get("secure_url").toString();

            System.out.println("Cloudinary URL: " + url);

            return url;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Photo upload failed: " + e.getMessage()
            );

        }
    }
}