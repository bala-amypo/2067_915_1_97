package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationLogRepository logRepository;
    private final CertificateRepository certificateRepository;

    public VerificationServiceImpl(
            VerificationLogRepository logRepository,
            CertificateRepository certificateRepository) {
        this.logRepository = logRepository;
        this.certificateRepository = certificateRepository;
    }

    public VerificationLog verifyCertificate(String code, String ip) {

        Certificate certificate = certificateRepository.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid certificate"));

        VerificationLog log = new VerificationLog();
        log.setCertificate(certificate);
        log.setIpAddress(ip);
        log.setVerifiedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        return logRepository.findByCertificateId(certificateId);
    }
}
