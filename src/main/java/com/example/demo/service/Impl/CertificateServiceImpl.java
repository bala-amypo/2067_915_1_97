@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final StudentRepository studentRepo;
    private final CertificateTemplateRepository templateRepo;
    private final CertificateRepository certRepo;

    public Certificate generateCertificate(Long studentId, Long templateId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        CertificateTemplate template = templateRepo.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        String code = "VC-" + UUID.randomUUID();

        Certificate cert = Certificate.builder()
                .student(student)
                .template(template)
                .issuedDate(LocalDate.now())
                .verificationCode(code)
                .qrCodeUrl("data:image/png;base64," + Base64.getEncoder().encodeToString(code.getBytes()))
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

    public List<Certificate> findByStudentId(Long studentId) {
        Student s = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return certRepo.findByStudent(s);
    }
}
