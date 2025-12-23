package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String rollNumber;

    public Student() {}

    public Student(String name, String email, String rollNumber) {
        this.name = name;
        this.email = email;
        this.rollNumber = rollNumber;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }
}
