package com.example.schooldairy.service;

import com.example.schooldairy.entity.Assignment;
import com.example.schooldairy.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repository;

    public Assignment addAssignment(
            Assignment assignment
    ) {

        return repository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {

        return repository.findAll();
    }

    public List<Assignment> getAssignmentsByClassSection(

            Integer studentClass,

            String section
    ) {

        return repository.findByStudentClassAndSection(
                studentClass,
                section
        );
    }
}
