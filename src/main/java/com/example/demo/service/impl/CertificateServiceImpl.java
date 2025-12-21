package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Student;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.CertificateService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository,
                                  StudentRepository studentRepository,
                                  CertificateTemplateRepository templateRepository) {
        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        CertificateTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found"));

        Certificate certificate = new Certificate();
        certificate.setStudent(student);
        certificate.setTemplate(template);
        certificate.setIssuedDate(LocalDate.now());
        certificate.setVerificationCode("VC-" + UUID.randomUUID());
        certificate.setQrCodeUrl("data:image/png;base64,DUMMY");

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificate(Long certificateId) {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public Certificate findByVerificationCode(String verificationCode) {
        return certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return certificateRepository.findByStudent(student);
    }
}
