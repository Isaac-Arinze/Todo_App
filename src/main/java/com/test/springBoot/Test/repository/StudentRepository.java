package com.test.springBoot.Test.repository;

import com.test.springBoot.Test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
