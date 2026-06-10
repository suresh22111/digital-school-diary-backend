package com.example.schooldairy.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(nullable = true)
    private String mobile;

    @Column(nullable = true)
    private String subject;

    @Enumerated(EnumType.STRING)
    private Role role;

}