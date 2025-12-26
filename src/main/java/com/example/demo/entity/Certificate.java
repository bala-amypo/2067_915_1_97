package com.example.demo.entity;

import jakarta.persistence.*; 
import lombok.*;
import java.time.LocalDate;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Certificate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Student student;
    
    @ManyToOne
    private CertificateTemplate template;
    
    private LocalDate issuedDate;
    private String qrCodeUrl; // Must be Base64 data URL
    
    @Column(unique = true)
    private String verificationCode; // Must start with "VC-"
}