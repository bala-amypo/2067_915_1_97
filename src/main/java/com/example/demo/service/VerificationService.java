package com.example.demo.service;

import com.example.demo.entity.VerificationLog;

public interface VerificationService {
    VerificationLog verifyCertificate(String code, String ip);
}
