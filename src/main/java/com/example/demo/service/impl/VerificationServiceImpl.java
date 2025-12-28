package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository certificateRepository,
                                   VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        VerificationLog log = new VerificationLog();
        log.setIpAddress(clientIp);
        log.setVerifiedAt(LocalDateTime.now());

        certificateRepository.findByVerificationCode(verificationCode)
                .ifPresentOrElse(
                        c -> log.setStatus("SUCCESS"),
                        () -> log.setStatus("FAILED")
                );

        return logRepository.save(log);
    }
}
