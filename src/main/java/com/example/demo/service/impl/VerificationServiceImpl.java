@Override
public VerificationLog verifyCertificate(String code, String ip) {

    Certificate cert = certRepo.findByVerificationCode(code).orElse(null);

    VerificationLog log = new VerificationLog(
            cert,
            code,
            ip,
            LocalDateTime.now()
    );

    return logRepo.save(log);
}
