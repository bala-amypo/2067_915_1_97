package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService service;

    public VerificationController(VerificationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<VerificationLog> verify(@RequestParam String verificationCode) {
        VerificationLog log = service.verifyCertificate(verificationCode, "127.0.0.1");
        return ResponseEntity.ok(log);
    }
}
