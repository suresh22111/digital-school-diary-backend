package com.example.schooldairy.controller;

import com.example.schooldairy.dto.ReportCardDTO;
import com.example.schooldairy.service.ReportCardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report-card")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReportCardController {

    private final ReportCardService
            reportCardService;

    @GetMapping("/{studentId}")
    public ReportCardDTO getReportCard(

            @PathVariable Long studentId

    ) {

        return reportCardService
                .generateReportCard(
                        studentId
                );
    }
}