package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository cr, VerificationLogRepository lr) {
        this.certificateRepository = cr;
        this.logRepository = lr;
    }

    @Override
    public VerificationLog verifyCertificate(String code, String ip) {
        Optional<Certificate> cert = certificateRepository.findByVerificationCode(code);
        
        VerificationLog log = VerificationLog.builder()
                .certificate(cert.orElse(null))
                .verifiedAt(LocalDateTime.now())
                .ipAddress(ip)
                .status(cert.isPresent() ? "SUCCESS" : "FAILED")
                .build();
        
        return logRepository.save(log);
    }
}