package com.test.springBoot.Test.service.impl;

import com.test.springBoot.Test.entity.Student;
import com.test.springBoot.Test.repository.StudentRepository;
import com.test.springBoot.Test.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("student not found"));
    }

    @Override
    public Student createstudent(Student student) {
//        Student newStudent = new Student();
        return studentRepository.save(student);


    }


}
