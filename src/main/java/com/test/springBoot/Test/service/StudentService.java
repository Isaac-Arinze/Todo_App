package com.test.springBoot.Test.service;

import com.test.springBoot.Test.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudent();

   Student getStudentById(Integer Id);

   Student createstudent(Student student);
}
