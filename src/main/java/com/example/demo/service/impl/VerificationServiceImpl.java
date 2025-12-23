@Override
public VerificationLog verifyCertificate(String code, String ip) {

    Certificate cert = certRepo.findByVerificationCode(code).orElse(null);

    VerificationLog log = new VerificationLog(
            cert,              // Certificate (can be null)
            code,              // verificationCode
            ip,                // ipAddress
            LocalDateTime.now()
    );

    return logRepo.save(log);
}
