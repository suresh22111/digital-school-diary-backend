package com.example.schooldairy.controller;

import com.example.schooldairy.entity.Parent;
import com.example.schooldairy.service.ParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.schooldairy.dto.ParentLoginRequest;
import com.example.schooldairy.dto.ParentLoginResponse;
import com.example.schooldairy.security.JwtService;

@RestController
@RequestMapping("/api/parents")
@CrossOrigin(origins = "*")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @Autowired
    private JwtService jwtService;

    // ==========================
    // Parent Registration
    // ==========================
    @PostMapping("/register")
    public ResponseEntity<?> registerParent(
            @RequestBody Parent parent
    ) {

        try {

            Parent savedParent =
                    parentService.registerParent(parent);

            return ResponseEntity.ok(savedParent);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }
    }

    // ==========================
    // Parent Login
    // ==========================
    @PostMapping("/login")
    public ResponseEntity<?> login(

            @RequestBody ParentLoginRequest request

    ) {

        System.out.println("Parent Login API Called");

        try {

            Parent parent =
                    parentService.login(

                            request.getMobile(),

                            request.getPassword()
                    );

            String token =
                    jwtService.generateToken(

                            parent.getMobile()
                    );

            ParentLoginResponse response =

                    new ParentLoginResponse(

                            token,

                            parent.getParentName(),

                            parent.getStudentId()

                    );

            return ResponseEntity.ok(
                    response
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }

}