package com.test.springBoot.Test.controller;

import com.test.springBoot.Test.entity.Student;
import com.test.springBoot.Test.repository.StudentRepository;
import com.test.springBoot.Test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class StudentController {


    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<Student> getAllStudent(){
        studentService.getAllStudent();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        Student students = studentService.createstudent(student);
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }
}
