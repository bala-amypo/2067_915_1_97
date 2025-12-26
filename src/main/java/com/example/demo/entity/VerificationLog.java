package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    private String verificationCode;
    private String status; // "SUCCESS" or "FAILED"
    private String ipAddress;
    private LocalDateTime verifiedAt;
}