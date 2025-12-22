package com.example.demo.service.impl;

import com.example.demo.model.Certificate;
import com.example.demo.model.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service   // ⭐ REQUIRED
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository verificationLogRepository;

    // Constructor injection
    public VerificationServiceImpl(
            CertificateRepository certificateRepository,
            VerificationLogRepository verificationLogRepository) {
        this.certificateRepository = certificateRepository;
        this.verificationLogRepository = verificationLogRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {

        Certificate certificate =
                certificateRepository.findByVerificationCode(verificationCode)
                        .orElse(null);

        VerificationLog log = VerificationLog.builder()
                .certificate(certificate)
                .verifiedAt(LocalDateTime.now())
                .ipAddress(clientIp)
                .status(certificate != null ? "SUCCESS" : "FAILED")
                .build();

        return verificationLogRepository.save(log);
    }
}
