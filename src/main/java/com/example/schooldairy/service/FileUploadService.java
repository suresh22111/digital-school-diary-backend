package com.example.schooldairy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    private final String uploadDir =
            "uploads/";

    public String uploadFile(
            MultipartFile file
    ) {

        try {

            File dir =
                    new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + file.getOriginalFilename();

            File destination =
                    new File(uploadDir + fileName);

            file.transferTo(destination);

            return fileName;

        } catch (IOException e) {

            throw new RuntimeException(
                    "File Upload Failed"
            );
        }
    }
}