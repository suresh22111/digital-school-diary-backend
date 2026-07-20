package com.example.schooldairy.service;

import com.example.schooldairy.entity.Homework;
import com.example.schooldairy.entity.NotificationType;
import com.example.schooldairy.repository.HomeworkRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    private final FileStorageService
            fileStorageService;

    private final StudentRepository studentRepository;

    private final NotificationService notificationService;

    // ======================================
    // Upload Homework
    // ======================================

    public Homework uploadHomework(

            String studentClass,
            String section,
            String subject,
            String content,
            String uploadedBy,
            MultipartFile file

    ) throws Exception {

        Homework hw = new Homework();

        hw.setStudentClass(studentClass);
        hw.setSection(section);
        hw.setSubject(subject);
        hw.setContent(content);
        hw.setUploadedBy(uploadedBy);

        hw.setUploadDate(
                LocalDate.now().toString()
        );
        hw.setExpiryDate(LocalDate.now().plusDays(1));

        // Optional File Upload

        if (file != null && !file.isEmpty()) {

            String fileName =
                    fileStorageService.saveFile(file);

            hw.setFileName(fileName);
        }

        Homework savedHomework =
                homeworkRepository.save(hw);

        notificationService.notifyClassStudents(

                Integer.parseInt(studentClass),

                section,

                "Homework Uploaded",

                "New homework has been uploaded for " + subject,

                NotificationType.HOMEWORK

        );

        return savedHomework;


    }

    // ======================================
    // Get All Homework
    // ======================================

    public List<Homework> getAllHomework() {

        return homeworkRepository.findAll();
    }

    // ======================================
    // Get Homework By Id
    // ======================================

    public Homework getHomeworkById(Long id) {

        return homeworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Homework not found with id: " + id));
    }

    // ======================================
    // Update Homework
    // ======================================

    public Homework updateHomework(

            Long id,
            String studentClass,
            String section,
            String subject,
            String content,
            MultipartFile file

    ) {

        try {

            Homework hw = getHomeworkById(id);

            hw.setStudentClass(studentClass);
            hw.setSection(section);
            hw.setSubject(subject);
            hw.setContent(content);
            hw.setUploadDate(
                    java.time.LocalDate.now().toString());
                    hw.setExpiryDate(LocalDate.now().plusDays(1));




            if (file != null && !file.isEmpty()) {

                String fileName =
                        fileStorageService.saveFile(file);

                hw.setFileName(fileName);
            }

            return homeworkRepository.save(hw);

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    // ======================================
    // Delete Homework
    // ======================================

    public void deleteHomework(Long id) {

        if (!homeworkRepository.existsById(id)) {

            throw new RuntimeException("Homework not found with id: " + id);
        }

        homeworkRepository.deleteById(id);
    }

    public List<Homework> getHomeworkByClassAndSection(
            String studentClass,
            String section
    ) {

        return homeworkRepository
                .findByStudentClassAndSection(
                        studentClass,
                        section
                );
    }

    public List<Homework>
    getActiveHomeworkByClassAndSection(

            String studentClass,

            String section
    ) {

        return homeworkRepository
                .getActiveHomeworkByClassAndSection(
                        studentClass,
                        section
                );
    }
}
