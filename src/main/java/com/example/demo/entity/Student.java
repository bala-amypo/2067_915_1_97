package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;

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