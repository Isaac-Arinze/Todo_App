package com.test.springBoot.Test.repository;

import com.test.springBoot.Test.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
