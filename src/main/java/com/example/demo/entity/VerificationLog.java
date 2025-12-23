package com.example.demo.entity;

import jakarta.persistence.*;
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
    private String status;
    private String ipAddress;
    private LocalDateTime verifiedAt;

    public VerificationLog() {}

    public VerificationLog(
            Certificate certificate,
            String verificationCode,
            String ipAddress,
            LocalDateTime verifiedAt
    ) {
        this.certificate = certificate;
        this.verificationCode = verificationCode;
        this.ipAddress = ipAddress;
        this.verifiedAt = verifiedAt;
        this.status = (certificate != null) ? "SUCCESS" : "FAILED";
    }

    // ---------- getters ----------
    public Long getId() { return id; }
    public Certificate getCertificate() { return certificate; }
    public String getVerificationCode() { return verificationCode; }
    public String getStatus() { return status; }
    public String getIpAddress() { return ipAddress; }
    public LocalDateTime getVerifiedAt() { return verifiedAt; }

    // ---------- setters (REQUIRED BY TESTS) ----------
    public void setStatus(String status) { this.status = status; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
