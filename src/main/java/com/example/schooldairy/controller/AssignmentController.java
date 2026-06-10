package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Assignment;
import com.example.schooldairy.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @PostMapping("/add")
    public Assignment addAssignment(
            @RequestBody Assignment assignment
    ) {

        return service.addAssignment(assignment);
    }

    @GetMapping("/all")
    public List<Assignment> getAllAssignments() {

        return service.getAllAssignments();
    }

    @GetMapping("/{studentClass}/{section}")
    public List<Assignment> getAssignmentsByClassSection(

            @PathVariable Integer studentClass,

            @PathVariable String section
    ) {

        return service.getAssignmentsByClassSection(
                studentClass,
                section
        );
    }
}
