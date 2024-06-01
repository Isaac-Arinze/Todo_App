package com.test.springBoot.Test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10, message = "text must be greater than 10")
    private String description;
    @ManyToOne (fetch = FetchType.LAZY)  // we dont want to fetch d user associated with a post
    @JsonIgnore
    private User user;

}
