package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "certificate_template",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "template_name")
    }
)
public class CertificateTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "template_name", nullable = false, unique = true)
    private String templateName;

    @Column(name = "background_url", nullable = false)
    private String backgroundUrl;

    @Column(name = "font_style", nullable = false)
    private String fontStyle;

    @Column(name = "signature_name", nullable = false)
    private String signatureName;

    // getters & setters (NO Lombok as per your request)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }
}
