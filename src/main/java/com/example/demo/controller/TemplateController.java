package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@Tag(name = "Certificate Templates")
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add certificate template")
    public CertificateTemplate addTemplate(
            @RequestBody CertificateTemplate template) {
        return service.addTemplate(template);
    }

    @GetMapping
    @Operation(summary = "Get all certificate templates")
    public List<CertificateTemplate> getAllTemplates() {
        return service.getAllTemplates();
    }
}
