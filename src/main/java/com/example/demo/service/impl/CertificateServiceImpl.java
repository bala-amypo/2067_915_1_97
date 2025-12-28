public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certRepo;
    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;

    public CertificateServiceImpl(CertificateRepository c,
                                  StudentRepository s,
                                  CertificateTemplateRepository t) {
        this.certRepo = c;
        this.studentRepo = s;
        this.templateRepo = t;
    }

    public Certificate generateCertificate(Long sid, Long tid) {
        Student s = studentRepo.findById(sid)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        CertificateTemplate t = templateRepo.findById(tid)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        String code = "VC-" + UUID.randomUUID();

        Certificate cert = Certificate.builder()
                .student(s)
                .template(t)
                .issuedDate(LocalDate.now())
                .verificationCode(code)
                .qrCodeUrl("data:image/png;base64," +
                        Base64.getEncoder().encodeToString(code.getBytes()))
                .build();

        return certRepo.save(cert);
    }

    public Certificate getCertificate(Long id) {
        return certRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public Certificate findByVerificationCode(String code) {
        return certRepo.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public List<Certificate> findByStudentId(Long sid) {
        Student s = studentRepo.findById(sid)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return certRepo.findByStudent(s);
    }
}
