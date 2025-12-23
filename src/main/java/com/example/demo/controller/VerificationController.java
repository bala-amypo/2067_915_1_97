package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;

public class VerificationController {

    private final VerificationService service;

    public VerificationController(VerificationService service) {
        this.service = service;
    }

    public VerificationLog verify(String verificationCode) {
        return service.verifyCertificate(verificationCode, "127.0.0.1");
    }
}
