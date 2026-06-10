package com.example.schooldairy.service;

import com.example.schooldairy.entity.MarksSheet;
import com.example.schooldairy.repository.MarksSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksSheetService {

    @Autowired
    private MarksSheetRepository repository;

    public MarksSheet addMarks(
            MarksSheet marksSheet
    ) {

        return repository.save(marksSheet);
    }

    public List<MarksSheet> getAllMarks() {

        return repository.findAll();
    }

    public List<MarksSheet> getMarksByClassSection(

            Integer studentClass,

            String section
    ) {

        return repository.findByStudentClassAndSection(
                studentClass,
                section
        );
    }

    public List<MarksSheet> getMarksByExam(

            Integer studentClass,

            String section,

            String examName
    ) {

        return repository.findByStudentClassAndSectionAndExamName(
                studentClass,
                section,
                examName
        );
    }
}
