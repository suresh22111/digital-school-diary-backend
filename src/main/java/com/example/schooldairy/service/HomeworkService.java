package com.example.schooldairy.service;

import com.example.schooldairy.entity.Homework;
import com.example.schooldairy.repository.HomeworkRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {

    private final HomeworkRepository repository;

    private final FileStorageService
            fileStorageService;

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

        // Optional File Upload

        if (file != null && !file.isEmpty()) {

            String fileName =
                    fileStorageService.saveFile(file);

            hw.setFileName(fileName);
        }

        return repository.save(hw);
    }

    // ======================================
    // Get All Homework
    // ======================================

    public List<Homework> getAllHomework() {

        return repository.findAll();
    }

    // ======================================
    // Get Homework By Id
    // ======================================

    public Homework getHomeworkById(Long id) {

        return repository.findById(id)
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

            if (file != null && !file.isEmpty()) {

                String fileName =
                        fileStorageService.saveFile(file);

                hw.setFileName(fileName);
            }

            return repository.save(hw);

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    // ======================================
    // Delete Homework
    // ======================================

    public void deleteHomework(Long id) {

        if (!repository.existsById(id)) {

            throw new RuntimeException("Homework not found with id: " + id);
        }

        repository.deleteById(id);
    }
}
