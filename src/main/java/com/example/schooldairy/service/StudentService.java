package com.example.schooldairy.service;

import com.example.schooldairy.entity.Parent;
import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.ParentRepository;
import com.example.schooldairy.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================
    // Add Student
    // ==========================
    public Student addStudent(Student student) {

        Integer studentClass = student.getStudentClass();

        Student lastStudent =
                repository.findTopByStudentClassOrderByStudentIdDesc(studentClass);

        Long nextStudentId;

        if (lastStudent == null) {

            nextStudentId = Long.parseLong(studentClass + "001");

        } else {

            nextStudentId = lastStudent.getStudentId() + 1;
        }

        student.setStudentId(nextStudentId);

        // Temporary compatibility
        student.setName(student.getFullName());

        // Save Student
        Student savedStudent = repository.save(student);

        // Automatically create Parent Account
        createParentAccount(savedStudent);

        return savedStudent;
    }

    // ==========================
    // Get All Students
    // ==========================
    public List<Student> getAllStudents() {

        return repository.findAll();
    }

    // ==========================
    // Delete Student
    // ==========================
    public void deleteStudent(Long id) {

        repository.deleteById(id);
    }

    // ==========================
    // Get Student By Student ID
    // ==========================
    public Student getStudentByStudentId(Long studentId) {

        return repository.findByStudentId(studentId)
                .orElse(null);
    }

    // ==========================
    // Update Student
    // ==========================
    public Student updateStudent(Long id, Student student) {

        Student existing = repository.findById(id)
                .orElseThrow();

        existing.setSurname(student.getSurname());
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());

        // Temporary compatibility
        existing.setName(existing.getFullName());

        existing.setStudentClass(student.getStudentClass());

        existing.setSection(student.getSection());

        existing.setFatherName(student.getFatherName());

        existing.setMotherName(student.getMotherName());

        existing.setParentMobile(student.getParentMobile());

        existing.setEmail(student.getEmail());

        existing.setGender(student.getGender());

        existing.setDateOfBirth(student.getDateOfBirth());

        existing.setAddress(student.getAddress());

        existing.setPhotoUrl(student.getPhotoUrl());

        return repository.save(existing);
    }

    // ==========================
    // Get Students By Class
    // ==========================
    public List<Student> getStudentsByClassAndSection(
            int studentClass,
            String section
    ) {

        return repository.findByStudentClassAndSection(
                studentClass,
                section
        );
    }

    // ==========================
    // Get Student By Mobile
    // ==========================
    public Student getStudentByMobile(String mobile) {

        return repository.findByParentMobile(mobile);
    }

    // ==========================
    // Generate Parent Password
    // ==========================
    private String generateParentPassword(Student student) {

        String firstName = student.getFirstName();

        if (firstName == null || firstName.isBlank()) {
            firstName = "user";
        }

        firstName = firstName.trim();

        String firstFour =
                firstName.length() >= 4
                        ? firstName.substring(0, 4).toLowerCase()
                        : firstName.toLowerCase();

        int year = student.getDateOfBirth().getYear();

        return firstFour + year;
    }

    // ==========================
    // Create Parent Account Automatically
    // ==========================
    private void createParentAccount(Student student) {

        System.out.println("createParentAccount() called");

        if (student.getParentMobile() == null ||
                student.getParentMobile().isBlank()) {

            System.out.println("Parent mobile is empty");
            return;
        }

        System.out.println("Parent Mobile: " + student.getParentMobile());

        if (parentRepository.existsByMobile(student.getParentMobile())) {

            System.out.println("Parent already exists");
            return;
        }

        System.out.println("Creating new parent account...");

        Parent parent = new Parent();

        parent.setStudentId(student.getStudentId().intValue());
        parent.setParentName(student.getFatherName());
        parent.setMobile(student.getParentMobile());

        String plainPassword = generateParentPassword(student);

        parent.setPassword(passwordEncoder.encode(plainPassword));
        parent.setRole("PARENT");

        parentRepository.save(parent);

        System.out.println("====================================");
        System.out.println("Parent Account Created Successfully");
        System.out.println("Username : " + parent.getMobile());
        System.out.println("Password : " + plainPassword);
        System.out.println("====================================");
    }
}