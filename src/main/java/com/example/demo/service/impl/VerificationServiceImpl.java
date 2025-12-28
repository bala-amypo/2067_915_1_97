public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certRepo;
    private final VerificationLogRepository logRepo;

    public VerificationServiceImpl(CertificateRepository c, VerificationLogRepository l) {
        this.certRepo = c;
        this.logRepo = l;
    }

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
}
