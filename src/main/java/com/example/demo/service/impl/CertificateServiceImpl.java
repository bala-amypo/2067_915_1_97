package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;

import java.util.*;
import org.springframework.stereotype.Service;  // ✅ Import added

@Service
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

        String verificationCode = "VC-" + UUID.randomUUID();
        String certificateNumber = "CERT-" + System.currentTimeMillis();
        String pdfPath = "certificates/" + certificateNumber + ".pdf";

        Certificate cert = new Certificate(
                certificateNumber,
                verificationCode,
                pdfPath,
                student,
                template
        );

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
