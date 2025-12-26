package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificate_template")
public class CertificateTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name", unique = true, nullable = false)
    private String templateName;

    @Column(name = "background_url", nullable = false)
    private String backgroundUrl;

    @Column(name = "font_style")
    private String fontStyle;

    @Column(name = "signature_name")
    private String signatureName;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getBackgroundUrl() { return backgroundUrl; }
    public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; }

    public String getFontStyle() { return fontStyle; }
    public void setFontStyle(String fontStyle) { this.fontStyle = fontStyle; }

    public String getSignatureName() { return signatureName; }
    public void setSignatureName(String signatureName) { this.signatureName = signatureName; }
}
