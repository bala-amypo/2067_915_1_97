package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;

import java.time.LocalDateTime;

public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certRepo;
    private final VerificationLogRepository logRepo;

    public VerificationServiceImpl(
            CertificateRepository certRepo,
            VerificationLogRepository logRepo
    ) {
        this.certRepo = certRepo;
        this.logRepo = logRepo;
    }

    @Override
    public VerificationLog verifyCertificate(String code, String ip) {

        Certificate cert = certRepo.findByVerificationCode(code).orElse(null);

        VerificationLog log = new VerificationLog(
                cert,
                code,
                ip,
                LocalDateTime.now()
        );

        return logRepo.save(log);
    }
}
