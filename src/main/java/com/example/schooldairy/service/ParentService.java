package com.example.schooldairy.service;

import com.example.schooldairy.entity.Parent;
import com.example.schooldairy.repository.ParentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    @Autowired
    private ParentRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================
    // Parent Registration
    // ==========================
    public Parent registerParent(
            Parent parent
    ) {

        if (repository.existsByMobile(
                parent.getMobile()
        )) {

            throw new RuntimeException(
                    "Mobile Number Already Registered"
            );
        }

        parent.setPassword(

                passwordEncoder.encode(
                        parent.getPassword()
                )
        );

        parent.setRole(
                "PARENT"
        );

        return repository.save(
                parent
        );
    }

    // ==========================
    // Parent Login
    // ==========================
    public Parent login(

            String mobile,

            String password
    ) {

        Parent parent =
                repository.findByMobile(
                        mobile
                ).orElseThrow(

                        () -> new RuntimeException(
                                "Invalid Mobile Number"
                        )
                );

        if (!passwordEncoder.matches(

                password,

                parent.getPassword()

        )) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        return parent;
    }

}