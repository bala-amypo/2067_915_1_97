package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Certificate {

    @Id
    @GeneratedValue
    private Long id;

    private String certificateNumber;
    private String verificationCode;
    private String pdfPath;

    @ManyToOne
    private Student student;

    @ManyToOne
    private CertificateTemplate template;

    public Certificate() {}

    public Certificate(String certificateNumber, String verificationCode,
                       String pdfPath, Student student, CertificateTemplate template) {
        this.certificateNumber = certificateNumber;
        this.verificationCode = verificationCode;
        this.pdfPath = pdfPath;
        this.student = student;
        this.template = template;
    }

    public Long getId() { return id; }
    public String getCertificateNumber() { return certificateNumber; }
    public String getVerificationCode() { return verificationCode; }
    public String getPdfPath() { return pdfPath; }
    public Student getStudent() { return student; }
    public CertificateTemplate getTemplate() { return template; }
}
