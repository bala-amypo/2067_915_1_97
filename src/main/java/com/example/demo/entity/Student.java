package com.example.demo.entity;

import jakarta.persistence.*; // Changed from javax to jakarta
import lombok.*;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String rollNumber;
}