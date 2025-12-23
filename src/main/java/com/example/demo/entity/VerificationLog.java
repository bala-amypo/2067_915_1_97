package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    private String verificationCode;
    private String ipAddress;
    private LocalDateTime verifiedAt;

    public VerificationLog() {}

    public VerificationLog(Certificate certificate,
                           String verificationCode,
                           String ipAddress,
                           LocalDateTime verifiedAt) {
        this.certificate = certificate;
        this.verificationCode = verificationCode;
        this.ipAddress = ipAddress;
        this.verifiedAt = verifiedAt;
    }

    public Long getId() {
        return id;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }
}
