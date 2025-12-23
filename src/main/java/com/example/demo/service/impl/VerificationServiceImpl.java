package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationService;

import java.time.LocalDateTime;

public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certRepo;
    private final VerificationLogRepository logRepo;

    public VerificationServiceImpl(CertificateRepository certRepo,
                                   VerificationLogRepository logRepo) {
        this.certRepo = certRepo;
        this.logRepo = logRepo;
    }

    @Override
    public VerificationLog verifyCertificate(String code, String ip) {
        Certificate cert = certRepo.findByVerificationCode(code).orElse(null);

        VerificationLog log = VerificationLog.builder()
                .certificate(cert)
                .verifiedAt(LocalDateTime.now())
                .status(cert != null ? "SUCCESS" : "FAILED")
                .ipAddress(ip)
                .build();

        return logRepo.save(log);
    }
}
