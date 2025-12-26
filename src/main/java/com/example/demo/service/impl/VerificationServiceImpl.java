package com.example.demo.service.impl;

import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private VerificationLogRepository repository;

    @Override
    public VerificationLog verify() {
        VerificationLog log = new VerificationLog();
        log.setStatus("VERIFIED");
        return repository.save(log);
    }
}
