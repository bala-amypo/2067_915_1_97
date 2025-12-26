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
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository cr, StudentRepository sr, CertificateTemplateRepository tr) {
        this.certificateRepository = cr;
        this.studentRepository = sr;
        this.templateRepository = tr;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        CertificateTemplate template = templateRepository.findById(templateId)
            .orElseThrow(() -> new RuntimeException("Template not found"));

        Certificate certificate = Certificate.builder()
            .student(student)
            .template(template)
            .issuedDate(LocalDate.now())
            .verificationCode("VC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .qrCodeUrl("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...")
            .build();

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certificateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }
}