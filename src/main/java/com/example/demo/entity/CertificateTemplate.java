package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CertificateTemplate {

    @Id
    @GeneratedValue
    private Long id;

    private String templateName;
    private String content;

    public CertificateTemplate() {}

    public CertificateTemplate(String templateName, String content) {
        this.templateName = templateName;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getTemplateName() { return templateName; }
    public String getContent() { return content; }
}
