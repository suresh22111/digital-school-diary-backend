package com.example.schooldairy.service;

import com.example.schooldairy.entity.ExamMarks;
import com.example.schooldairy.repository.ExamMarksRepository;
import com.example.schooldairy.entity.SubjectMarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamMarksService {


    @Autowired
    private ExamMarksRepository repository;


    public ExamMarks addMarks(
            ExamMarks examMarks
    ) {

        System.out.println(
                "Subjects Count = "
                        + examMarks.getSubjects().size()
        );

        examMarks.getSubjects()
                .forEach(
                        subject ->
                                System.out.println(
                                        subject.getSubject()
                                )
                );

        return repository.save(
                examMarks
        );
    }


    public List<ExamMarks> getAllMarks() {

        return repository.findAll();
    }


    public List<ExamMarks> getStudentMarks(
            Long studentId
    ) {

        return repository
                .findByStudentId(
                        studentId
                );
    }


    public List<ExamMarks> getStudentExamMarks(
            Long studentId,
            String examName
    ) {

        return repository
                .findByStudentIdAndExamName(
                        studentId,
                        examName
                );
    }


    public List<ExamMarks> getClassExamMarks(
            Integer studentClass,
            String section,
            String examName
    ) {

        return repository
                .findByStudentClassAndSectionAndExamName(
                        studentClass,
                        section,
                        examName
                );
    }

    public ExamMarks getMarksById(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public ExamMarks updateMarks(
            Long id,
            ExamMarks updatedMarks
    ) {

        ExamMarks existing =
                repository.findById(id)
                        .orElseThrow();

        existing.setStudentId(
                updatedMarks.getStudentId()
        );

        existing.setStudentName(
                updatedMarks.getStudentName()
        );

        existing.setStudentClass(
                updatedMarks.getStudentClass()
        );

        existing.setSection(
                updatedMarks.getSection()
        );

        existing.setExamName(
                updatedMarks.getExamName()
        );

        for (
                int i = 0;
                i < existing.getSubjects().size();
                i++
        ) {

            SubjectMarks existingSubject =
                    existing.getSubjects().get(i);

            SubjectMarks updatedSubject =
                    updatedMarks.getSubjects().get(i);

            existingSubject.setMarksObtained(
                    updatedSubject.getMarksObtained()
            );

            existingSubject.setMaxMarks(
                    updatedSubject.getMaxMarks()
            );

            existingSubject.setGrade(
                    updatedSubject.getGrade()
            );

            existingSubject.setRemarks(
                    updatedSubject.getRemarks()
            );
        }

        return repository.save(existing);
    }
    public void deleteMarks(
            Long id
    ) {

        repository.deleteById(id);
    }

    public ExamMarks saveMarks(
            ExamMarks examMarks
    ) {

        List<ExamMarks> existingRecords =
                repository.findByStudentIdAndExamName(
                        examMarks.getStudentId(),
                        examMarks.getExamName()
                );

        if (!existingRecords.isEmpty()) {

            throw new RuntimeException(
                    "Marks already entered for this Exam"
            );
        }

        return repository.save(examMarks);
    }
}