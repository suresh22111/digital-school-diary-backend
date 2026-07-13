package com.example.schooldairy.controller;

import com.example.schooldairy.dto.ReportCardDTO;
import com.example.schooldairy.service.ReportCardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/report-card")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReportCardController {

    private final ReportCardService reportCardService;

    @GetMapping("/{studentId}")
    public ReportCardDTO getReportCard(

            @PathVariable Long studentId,

            @RequestParam String examName

    ) {

        return reportCardService
                .generateReportCard(
                        studentId,
                        examName
                );
    }

    @GetMapping("/pdf/{studentId}")
    public ResponseEntity<byte[]> downloadReportCard(

            @PathVariable Long studentId,

            @RequestParam String examName

    ) {

        try {

            ReportCardDTO report =
                    reportCardService
                            .generateReportCard(
                                    studentId,
                                    examName
                            );

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    out
            );

            document.open();

            // ==========================
            // SCHOOL LOGO
            // ==========================

          /*  Image logo =
                    Image.getInstance(
                            "src/main/resources/static/logo.jpg"
                    );

            logo.scaleToFit(
                    80,
                    80
            );

            logo.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(
                    logo
            ); */

            // ==========================
            // SCHOOL NAME
            // ==========================

            Font schoolFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            20
                    );

            Paragraph schoolName =
                    new Paragraph(
                            "Z P P High School",
                            schoolFont
                    );

            schoolName.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(
                    schoolName
            );

            Paragraph address =
                    new Paragraph(
                            "A.Kothapalli,Thondangi (M), Kakinada (D) Andhra Pradesh"
                    );

            address.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(
                    address
            );

            document.add(
                    new Paragraph(" ")
            );

            // ==========================
            // REPORT CARD TITLE
            // ==========================

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            16
                    );

            Paragraph title =
                    new Paragraph(
                            "STUDENT REPORT CARD",
                            titleFont
                    );

            title.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(
                    title
            );

            document.add(
                    new Paragraph(
                                    "-------------------------------------------------------------"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            // ==========================
            // STUDENT DETAILS
            // ==========================

            document.add(
                    new Paragraph(
                            "Student Name : "
                                    + report.getStudentName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Student ID : "
                                    + report.getStudentId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Class : "
                                    + report.getStudentClass()
                    )
            );

            document.add(
                    new Paragraph(
                            "Section : "
                                    + report.getSection()
                    )
            );

            document.add(
                    new Paragraph(
                            "Exam : "
                                    + report.getExamName()
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            // ==========================
            // SUBJECT TABLE
            // ==========================

            PdfPTable table =
                    new PdfPTable(3);

            table.setWidthPercentage(
                    100
            );

            table.addCell(
                    "Subject"
            );

            table.addCell(
                    "Marks Obtained"
            );

            table.addCell(
                    "Max Marks"
            );

            report.getSubjects().forEach(subject -> {

                table.addCell(
                        subject.getSubject()
                );

                table.addCell(
                        String.valueOf(
                                subject.getMarksObtained()
                        )
                );

                table.addCell(
                        String.valueOf(
                                subject.getMaxMarks()
                        )
                );
            });

            document.add(
                    table
            );

            document.add(
                    new Paragraph(" ")
            );

            // ==========================
            // RESULT SUMMARY
            // ==========================

            document.add(
                    new Paragraph(
                            "Total Marks : "
                                    + report.getTotalMarksObtained()
                                    + " / "
                                    + report.getTotalMaxMarks()
                    )
            );

            document.add(
                    new Paragraph(
                            "Percentage : "
                                    + report.getPercentage()
                                    + "%"
                    )
            );

            document.add(
                    new Paragraph(
                            "Attendance : "
                                    + report.getAttendancePercentage()
                                    + "%"
                    )
            );

            document.add(
                    new Paragraph(
                            "Grade : "
                                    + report.getFinalGrade()
                    )
            );

            document.add(
                    new Paragraph(
                            "Remarks : "
                                    + report.getRemarks()
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(" ")
            );

            // ==========================
            // SIGNATURES
            // ==========================

            document.add(
                    new Paragraph(
                            "Class Teacher                              Principal"
                    )
            );

            document.close();

            return ResponseEntity.ok()

                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=ReportCard.pdf"
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