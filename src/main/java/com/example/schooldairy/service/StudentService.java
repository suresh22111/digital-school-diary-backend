package com.example.schooldairy.service;

import com.example.schooldairy.entity.Student;
import com.example.schooldairy.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student addStudent(
            Student student
    ) {

        return repository.save(student);
    }

    public List<Student> getAllStudents() {

        return repository.findAll();
    }
    public void deleteStudent(Long id) {

        repository.deleteById(id);

    }

    public Student getStudentByStudentId(
            Long studentId
    ) {

        return repository
                .findByStudentId(studentId)
                .orElse(null);
    }

    public Student updateStudent(
            Long id,
            Student student
    ) {

        Student existing =
                repository.findById(id)
                        .orElseThrow();

        existing.setStudentId(
                student.getStudentId()
        );

        existing.setName(
                student.getName()
        );

        existing.setStudentClass(
                student.getStudentClass()
        );

        existing.setSection(
                student.getSection()
        );

        existing.setFatherName(
                student.getFatherName()
        );

        existing.setMotherName(
                student.getMotherName()
        );

        existing.setParentMobile(
                student.getParentMobile()
        );

        existing.setAddress(
                student.getAddress()
        );

        existing.setEmail(
                student.getEmail()
        );

        existing.setGender(
                student.getGender()
        );

        existing.setDateOfBirth(
                student.getDateOfBirth()
        );

        return repository.save(existing);
    }
}