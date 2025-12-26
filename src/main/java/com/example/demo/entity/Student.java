package com.example.demo.entity;

import jakarta.persistence.*; // CHANGE THIS FROM javax.persistence.*
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String rollNumber;
}