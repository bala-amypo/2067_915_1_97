package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CertificateTemplate> add(@RequestBody CertificateTemplate template) {
        return ResponseEntity.ok(service.addTemplate(template));
    }

    @GetMapping
    public ResponseEntity<List<CertificateTemplate>> list() {
        return ResponseEntity.ok(service.getAllTemplates());
    }
}
