package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.service.StudentService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/id-card")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentIdCardController {

    private final StudentService studentService;

    @Value("${school.name}")
    private String schoolName;

    @GetMapping("/{studentId}")
    public ResponseEntity<byte[]> downloadIdCard(
            @PathVariable Long studentId
    ) {

        try {

            Student student =
                    studentService.getStudentByStudentId(studentId);

            if (student == null) {

                throw new RuntimeException(
                        "Student Not Found"
                );
            }

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            Document document =
                    new Document(
                            new Rectangle(
                                    300,
                                    600
                            )
                    );

            PdfWriter.getInstance(
                    document,
                    out
            );

            document.open();

            Font schoolFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            16
                    );

            Paragraph schoolTitle =
                    new Paragraph(
                            schoolName,
                            schoolFont
                    );

            schoolTitle.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(schoolTitle);

            document.add(
                    new Paragraph(" ")
            );

            try {

                Image logo =
                        Image.getInstance(
                                "src/main/resources/static/logo.jpg"
                        );

                logo.scaleToFit(
                        60,
                        60
                );

                logo.setAlignment(
                        Element.ALIGN_CENTER
                );

                document.add(logo);

            } catch (Exception e) {

                System.out.println(
                        "Logo Not Found"
                );
            }

            document.add(
                    new Paragraph(" ")
            );

            try {

                if (
                        student.getPhotoUrl() != null
                ) {

                    String imagePath =
                            student.getPhotoUrl()
                                    .replace(
                                            "http://localhost:8080/",
                                            ""
                                    );

                    Image photo =
                            Image.getInstance(
                                    imagePath
                            );

                    photo.scaleToFit(
                            100,
                            100
                    );

                    photo.setAlignment(
                            Element.ALIGN_CENTER
                    );

                    document.add(photo);
                }

            } catch (Exception e) {

                System.out.println(
                        "Photo Not Found"
                );
            }

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "Student ID : "
                                    + student.getStudentId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Name : "
                                    + student.getName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Class : "
                                    + student.getStudentClass()
                    )
            );

            document.add(
                    new Paragraph(
                            "Section : "
                                    + student.getSection()
                    )
            );

            document.add(
                    new Paragraph(
                            "Parent Mobile : "
                                    + student.getParentMobile()
                    )
            );

            document.add(
                    new Paragraph(
                            "Father Name : "
                                    + student.getFatherName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Email : "
                                    + student.getEmail()
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            Paragraph sign =
                    new Paragraph(
                            "Principal Signature"
                    );

            sign.setAlignment(
                    Element.ALIGN_RIGHT
            );

            document.add(sign);

            document.close();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=Student_ID_Card.pdf"
                    )
                    .contentType(
                            MediaType.APPLICATION_PDF
                    )
                    .body(
                            out.toByteArray()
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }
}
