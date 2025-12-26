package com.example.demo.entity;

import jakarta.persistence.*; // Required for Spring Boot 3
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private CertificateTemplate template;

    private LocalDate issuedDate;

    // Requirement: Must be a Base64 data URL string
    private String qrCodeUrl;

    @Column(unique = true)
    private String verificationCode; // Requirement: Must start with "VC-"
}