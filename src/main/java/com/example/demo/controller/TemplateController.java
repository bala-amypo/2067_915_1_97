package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@Tag(name = "Certificate Templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Operation(summary = "Add new certificate template")
    @PostMapping
    public ResponseEntity<CertificateTemplate> addTemplate(
            @RequestBody CertificateTemplate template) {

        CertificateTemplate saved = templateService.addTemplate(template);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all certificate templates")
    @GetMapping
    public ResponseEntity<List<CertificateTemplate>> getAllTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
}
