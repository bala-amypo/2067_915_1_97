package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/generate/{studentId}/{templateId}")
    public Certificate generate(
            @PathVariable Long studentId,
            @PathVariable Long templateId) {
        return certificateService.generateCertificate(studentId, templateId);
    }

    @GetMapping("/{certificateId}")
    public Certificate getCertificate(@PathVariable Long certificateId) {
        return certificateService.getCertificate(certificateId);
    }

    @GetMapping("/verify/code/{verificationCode}")
    public Certificate verify(@PathVariable String verificationCode) {
        return certificateService.findByVerificationCode(verificationCode);
    }
}
