package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CertificateService;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final StudentRepository studentRepository;
    private final CertificateRepository certificateRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(
            StudentRepository studentRepository,
            CertificateRepository certificateRepository,
            CertificateTemplateRepository templateRepository) {

        this.studentRepository = studentRepository;
        this.certificateRepository = certificateRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        Certificate certificate = new Certificate();
        certificate.setCertificateCode(UUID.randomUUID().toString());
        certificate.setIssueDate(LocalDate.now());
        certificate.setStudent(student);
        certificate.setTemplate(template);

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }
}
