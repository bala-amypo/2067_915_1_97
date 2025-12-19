private final CertificateTemplateRepository templateRepository;

public CertificateServiceImpl(
        StudentRepository studentRepository,
        CertificateRepository certificateRepository,
        CertificateTemplateRepository templateRepository) {

    this.studentRepository = studentRepository;
    this.certificateRepository = certificateRepository;
    this.templateRepository = templateRepository;
}
