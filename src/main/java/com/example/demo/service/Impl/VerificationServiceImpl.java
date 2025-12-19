@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certRepo;
    private final VerificationLogRepository logRepo;

    public VerificationLog verifyCertificate(String code, String ip) {
        Certificate cert = certRepo.findByVerificationCode(code).orElse(null);

        VerificationLog log = VerificationLog.builder()
                .certificate(cert)
                .verifiedAt(LocalDateTime.now())
                .status(cert != null ? "SUCCESS" : "FAILED")
                .ipAddress(ip)
                .build();

        return logRepo.save(log);
    }

    public List<VerificationLog> getLogsByCertificate(Long certId) {
        Certificate c = certRepo.findById(certId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
        return logRepo.findAll()
                .stream()
                .filter(l -> l.getCertificate().equals(c))
                .toList();
    }
}
