package com.example.schooldairy.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;
import com.example.schooldairy.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    // Add Student
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {

        return service.addStudent(student);
    }

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents() {

        return service.getAllStudents();
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {

        service.deleteStudent(id);
    }

    // Search by Student ID
    @GetMapping("/student-id/{studentId}")
    public Student getStudentByStudentId(
            @PathVariable Long studentId) {

        return service.getStudentByStudentId(studentId);
    }

    // Update Student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return service.updateStudent(id, student);
    }

    // Get Students by Class & Section
    @GetMapping("/class/{studentClass}/section/{section}")
    public List<Student> getStudentsByClassAndSection(
            @PathVariable int studentClass,
            @PathVariable String section) {

        return service.getStudentsByClassAndSection(
                studentClass,
                section
        );
    }

    // Get Student by Parent Mobile
    @GetMapping("/mobile/{mobile}")
    public Student getStudentByMobile(
            @PathVariable String mobile) {

        return service.getStudentByMobile(mobile);
    }

    // Upload Student Photo
    @PostMapping("/upload-photo")
    public String uploadPhoto(
            @RequestParam("file") MultipartFile file) {

        try {

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Photo upload failed: " + e.getMessage()
            );
        }
    }
}