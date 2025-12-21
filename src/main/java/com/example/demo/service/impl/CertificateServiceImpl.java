package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;
    private final CertificateRepository certRepo;

    public CertificateServiceImpl(StudentRepository studentRepo,
                                  CertificateTemplateRepository templateRepo,
                                  CertificateRepository certRepo) {
        this.studentRepo = studentRepo;
        this.templateRepo = templateRepo;
        this.certRepo = certRepo;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepo.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setTemplate(template);
        certificate.setIssuedDate(LocalDate.now());
        certificate.setVerificationCode("VC-" + UUID.randomUUID());
        certificate.setQrCodeUrl("data:image/png;base64," + certificate.getVerificationCode());

        return certRepo.save(certificate);
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certRepo.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }
}
