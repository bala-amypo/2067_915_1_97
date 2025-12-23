package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<Certificate> generate(
            @RequestParam Long studentId,
            @RequestParam Long templateId
    ) {
        Certificate cert = service.generateCertificate(studentId, templateId);
        return ResponseEntity.ok(cert);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCertificate(id));
    }
}
