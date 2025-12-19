package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final TemplateRepository templateRepository;

    public CertificateServiceImpl(
            CertificateRepository certificateRepository,
            StudentRepository studentRepository,
            TemplateRepository templateRepository) {
        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
        this.templateRepository = templateRepository;
    }

    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setTemplate(template);
        certificate.setIssuedDate(LocalDate.now());
        certificate.setVerificationCode(UUID.randomUUID().toString());

        return certificateRepository.save(certificate);
    }

    public Certificate getCertificate(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public Certificate findByVerificationCode(String code) {
        return certificateRepository.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid verification code"));
    }
}
