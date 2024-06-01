package com.test.springBoot.Test.controller;

import com.test.springBoot.Test.dao.UserDaoService;
import com.test.springBoot.Test.entity.Post;
import com.test.springBoot.Test.entity.User;
import com.test.springBoot.Test.exception.UserNotFoundException;
import com.test.springBoot.Test.repository.PostRepository;
import com.test.springBoot.Test.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;

    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/jpa/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        Optional<User>  user = userRepository.findById(id);
         if (user.isEmpty())
             throw new UserNotFoundException("id:" + id);

         EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder  link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
                return entityModel;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllPostForUser(@PathVariable int id) {
        //find d user first
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPosts();
    }
    
    @PostMapping("/jpa/users")
    public ResponseEntity<?> creatUser(@RequestBody User user) {
       User savedUser =  userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}" )
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location ).build();
    }
@PostMapping ("/jpa/users/{id}/posts")
    public ResponseEntity <Object> createPostForUser (@PathVariable int id, @RequestBody @Valid Post post){

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException("id: " + id);
        }

        post.setUser(user.get());
       Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();




    }
}