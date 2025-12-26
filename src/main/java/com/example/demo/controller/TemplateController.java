package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    private TemplateService service;

    @PostMapping
    public CertificateTemplate create(@RequestBody CertificateTemplate template) {
        return service.save(template);
    }
}
