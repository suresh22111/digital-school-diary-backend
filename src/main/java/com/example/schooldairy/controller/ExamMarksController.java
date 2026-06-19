package com.example.schooldairy.controller;

import com.example.schooldairy.entity.ExamMarks;
import com.example.schooldairy.service.ExamMarksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "*")
public class ExamMarksController {


    @Autowired
    private ExamMarksService service;






    // Get all students marks
    @GetMapping("/all")
    public List<ExamMarks> getAllMarks() {

        return service.getAllMarks();
    }


    // Get student all exams
    @GetMapping("/student/{studentId}")
    public List<ExamMarks> getStudentMarks(
            @PathVariable Long studentId
    ) {

        return service.getStudentMarks(
                studentId
        );
    }


    // Get student particular exam
    @GetMapping("/student-exam")
    public List<ExamMarks> getStudentExamMarks(

            @RequestParam Long studentId,

            @RequestParam String examName
    ) {

        return service.getStudentExamMarks(
                studentId,
                examName
        );
    }


    // Get class exam results
    @GetMapping("/class")
    public List<ExamMarks> getClassExamMarks(

            @RequestParam Integer studentClass,

            @RequestParam String section,

            @RequestParam String examName
    ) {

        return service.getClassExamMarks(
                studentClass,
                section,
                examName
        );
    }

    @GetMapping("/{id}")
    public ExamMarks getMarksById(
            @PathVariable Long id
    ) {

        return service.getMarksById(id);
    }

    @PutMapping("/update/{id}")
    public ExamMarks updateMarks(

            @PathVariable Long id,

            @RequestBody ExamMarks examMarks
    ) {

        return service.updateMarks(
                id,
                examMarks
        );
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMarks(
            @PathVariable Long id
    ) {

        service.deleteMarks(id);

        return "Exam Marks Deleted Successfully";
    }
    // Add complete exam marks
    @PostMapping("/add")
    public ResponseEntity<?> addMarks(
            @RequestBody ExamMarks examMarks
    ) {

        try {

            return ResponseEntity.ok(
                    service.saveMarks(examMarks)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}