package com.example.schooldairy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file)
            throws Exception {

        // File Optional

        if (file == null || file.isEmpty()) {

            return null;
        }

        Path uploadPath =
                Paths.get(uploadDir);

        // Create Folder

        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);
        }

        // File Name

        String fileName =

                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path filePath =
                uploadPath.resolve(fileName);

        // Save File

        Files.copy(

                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return fileName;
    }
}