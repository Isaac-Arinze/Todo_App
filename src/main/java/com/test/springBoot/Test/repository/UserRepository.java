package com.test.springBoot.Test.repository;

import com.test.springBoot.Test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
