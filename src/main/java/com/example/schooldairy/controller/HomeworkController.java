package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Homework;
import com.example.schooldairy.service.HomeworkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/homework")
@CrossOrigin(origins = "*")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    // =========================
    // UPLOAD HOMEWORK
    // =========================
    @PostMapping("/upload")
    public ResponseEntity<?> uploadHomework(

            @RequestParam("studentClass") String studentClass,

            @RequestParam("section") String section,

            @RequestParam("subject") String subject,

            @RequestParam("content") String content,

            @RequestParam("uploadedBy") String uploadedBy,

            @RequestParam(value = "file", required = false)
            MultipartFile file

    ) {

        try {

            Homework homework =
                    homeworkService.uploadHomework(
                            studentClass,
                            section,
                            subject,
                            content,
                            uploadedBy,
                            file
                    );

            return ResponseEntity.ok(homework);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================
    // GET ALL HOMEWORK
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<Homework>> getAllHomework() {

        return ResponseEntity.ok(
                homeworkService.getAllHomework()
        );
    }

    // =========================
    // GET HOMEWORK BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Homework> getHomeworkById(
            @PathVariable Long id
    ) {

        Homework homework =
                homeworkService.getHomeworkById(id);

        return ResponseEntity.ok(homework);
    }

    // =========================
    // DELETE HOMEWORK
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHomework(
            @PathVariable Long id
    ) {

        homeworkService.deleteHomework(id);

        return ResponseEntity.ok(
                "Homework Deleted Successfully"
        );
    }

    // =========================
    // UPDATE HOMEWORK
    // =========================
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHomework(

            @PathVariable Long id,

            @RequestParam("studentClass") String studentClass,
            @RequestParam("section") String section,
            @RequestParam("subject") String subject,
            @RequestParam("content") String content,

            @RequestParam(
                    value = "file",
                    required = false
            )
            MultipartFile file

    ) {

        try {

            Homework homework =
                    homeworkService.updateHomework(
                            id,
                            studentClass,
                            section,
                            subject,
                            content,
                            file
                    );

            return ResponseEntity.ok(homework);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/class/{studentClass}/section/{section}")
    public ResponseEntity<List<Homework>>
    getHomeworkByClassAndSection(

            @PathVariable String studentClass,

            @PathVariable String section

    ) {

        return ResponseEntity.ok(
                homeworkService
                        .getHomeworkByClassAndSection(
                                studentClass,
                                section
                        )
        );
    }
}
