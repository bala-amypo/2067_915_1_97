package com.example.demo.service.impl;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.TemplateService;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repo;

    public TemplateServiceImpl(CertificateTemplateRepository repo) {
        this.repo = repo;
    }

    @Override
    public CertificateTemplate addTemplate(CertificateTemplate template) {
        if (repo.findByTemplateName(template.getTemplateName()).isPresent()) {
            throw new RuntimeException("Template name exists");
        }
        return repo.save(template);
    }

    @Override
    public List<CertificateTemplate> getAllTemplates() {
        return repo.findAll();
    }

    @Override
    public CertificateTemplate findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }
}
