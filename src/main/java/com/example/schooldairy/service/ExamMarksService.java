package com.example.schooldairy.service;

import com.example.schooldairy.entity.ExamMarks;
import com.example.schooldairy.repository.ExamMarksRepository;
import com.example.schooldairy.entity.SubjectMarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.schooldairy.entity.NotificationType;
import com.example.schooldairy.service.NotificationService;


import java.util.List;

@Service
public class ExamMarksService {


    @Autowired
    private ExamMarksRepository repository;

    @Autowired
    private NotificationService notificationService;


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

            String grade =
                    calculateGrade(
                            updatedSubject.getMarksObtained(),
                            updatedSubject.getMaxMarks()
                    );

            existingSubject.setGrade(grade);

            existingSubject.setRemarks(
                    calculateRemarks(grade)
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

        // Calculate Grade & Remarks
        for (SubjectMarks subject : examMarks.getSubjects()) {

            String grade = calculateGrade(
                    subject.getMarksObtained(),
                    subject.getMaxMarks()
            );

            subject.setGrade(grade);

            subject.setRemarks(
                    calculateRemarks(grade)
            );

        }

        // Save Exam Marks
        ExamMarks savedMarks =
                repository.save(examMarks);

        // Create Notification for this Student
        notificationService.createNotification(

                examMarks.getStudentId(),

                "Exam Results Published",

                examMarks.getExamName()
                        + " marks have been published.",

                NotificationType.EXAM

        );

        return savedMarks;
    }

    private String calculateGrade(Integer obtained, Integer max) {

        double percentage =
                (obtained * 100.0) / max;

        if (percentage >= 90)
            return "A+";

        else if (percentage >= 75)
            return "A";

        else if (percentage >= 60)
            return "B";

        else if (percentage >= 35)
            return "C";

        else
            return "F";
    }


    private String calculateRemarks(String grade) {

        switch (grade) {

            case "A+":
                return "Excellent";

            case "A":
                return "Very Good";

            case "B":
                return "Good";

            case "C":
                return "Needs Improvement";

            default:
                return "Fail";
        }

    }
}