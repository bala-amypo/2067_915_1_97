package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CertificateTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;
    private String content;

    public CertificateTemplate() {}

    public Long getId() { return id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
