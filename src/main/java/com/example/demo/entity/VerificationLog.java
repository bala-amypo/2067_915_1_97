package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationLog {

    @Id
    @GeneratedValue
    private Long id;

    private String verificationCode;
    private String ipAddress;
    private LocalDateTime verifiedAt;

    public VerificationLog() {}

    public VerificationLog(String verificationCode, String ipAddress, LocalDateTime verifiedAt) {
        this.verificationCode = verificationCode;
        this.ipAddress = ipAddress;
        this.verifiedAt = verifiedAt;
    }

    public Long getId() { return id; }
    public String getVerificationCode() { return verificationCode; }
    public String getIpAddress() { return ipAddress; }
    public LocalDateTime getVerifiedAt() { return verifiedAt; }
}
