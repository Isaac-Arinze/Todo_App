package com.test.springBoot.Test.controller;

import com.test.springBoot.Test.dao.UserDaoService;
import com.test.springBoot.Test.entity.User;
import com.test.springBoot.Test.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
         User user = service.findById(id);
         if (user == null)
             throw new UserNotFoundException("id:" + id);
                return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        service.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> creatUser(@RequestBody User user) {
       User savedUser =  service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}" )
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location ).build();
    }
}