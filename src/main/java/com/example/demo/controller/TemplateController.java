package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public CertificateTemplate createTemplate(
            @RequestBody CertificateTemplate template) {

        // IMPORTANT: id must be null
        template.setId(null);

        return templateService.addTemplate(template);
    }

    @GetMapping
    public List<CertificateTemplate> getAllTemplates() {
        return templateService.getAllTemplates();
    }
}
