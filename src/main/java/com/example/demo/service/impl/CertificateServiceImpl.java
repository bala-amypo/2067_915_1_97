@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepo;
    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;

    public CertificateServiceImpl(CertificateRepository certificateRepo,
                                  StudentRepository studentRepo,
                                  CertificateTemplateRepository templateRepo) {
        this.certificateRepo = certificateRepo;
        this.studentRepo = studentRepo;
        this.templateRepo = templateRepo;
    }

    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepo.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        Certificate c = new Certificate();
        c.setStudent(student);
        c.setTemplate(template);
        c.setIssuedDate(LocalDate.now());
        c.setVerificationCode("VC-" + UUID.randomUUID());

        c.setQrCodeUrl("data:image/png;base64,DUMMY");

        return certificateRepo.save(c);
    }
}
