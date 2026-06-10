package com.example.schooldairy.controller;

import com.example.schooldairy.entity.MarksSheet;
import com.example.schooldairy.repository.MarksSheetRepository;
import com.example.schooldairy.service.MarksSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "*")
public class MarksSheetController {

    @Autowired
    private MarksSheetRepository marksSheetRepository ;

    @Autowired
    private MarksSheetService service;

    @PostMapping("/add")
    public MarksSheet addMarks(
            @RequestBody MarksSheet marksSheet
    ) {

        return service.addMarks(marksSheet);
    }

    @GetMapping("/all")
    public List<MarksSheet> getAllMarks() {

        return service.getAllMarks();
    }

    @GetMapping("/{studentClass}/{section}")
    public List<MarksSheet> getMarksByClassSection(

            @PathVariable Integer studentClass,

            @PathVariable String section
    ) {

        return service.getMarksByClassSection(
                studentClass,
                section
        );
    }

    @GetMapping("/{studentClass}/{section}/{examName}")
    public List<MarksSheet> getMarksByExam(

            @PathVariable Integer studentClass,

            @PathVariable String section,

            @PathVariable String examName
    ) {

        return service.getMarksByExam(
                studentClass,
                section,
                examName
        );
    }
    @GetMapping("/student/{studentId}")
    public List<MarksSheet> getMarksByStudentId(
            @PathVariable Long studentId
    ) {

        return marksSheetRepository
                .findByStudentId(studentId);
    }
}
