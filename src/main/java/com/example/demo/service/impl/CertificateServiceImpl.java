package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;

import java.time.LocalDate;
import java.util.*;

public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certRepo;
    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;

    public CertificateServiceImpl(
            CertificateRepository certRepo,
            StudentRepository studentRepo,
            CertificateTemplateRepository templateRepo) {
        this.certRepo = certRepo;
        this.studentRepo = studentRepo;
        this.templateRepo = templateRepo;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepo.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        String code = "VC-" + UUID.randomUUID();

        Certificate cert = Certificate.builder()
                .student(student)
                .template(template)
                .issuedDate(LocalDate.now())
                .verificationCode(code)
                .qrCodeUrl("data:image/png;base64," +
                        Base64.getEncoder().encodeToString(code.getBytes()))
                .build();

        return certRepo.save(cert);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String code) {
        return certRepo.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return certRepo.findByStudent(student);
    }
}
