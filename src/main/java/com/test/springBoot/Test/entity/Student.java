package com.test.springBoot.Test.entity;

import jakarta.persistence.*;



@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Integer Id;
    private String firstName;
    private String surName;
    private int age;



    public Student(Integer id, String firstName, String surName, int age) {

        super();
        Id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }

    public Student() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                '}';
    }
}
