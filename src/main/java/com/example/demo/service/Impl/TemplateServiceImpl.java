@Service
public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repository;

    public TemplateServiceImpl(CertificateTemplateRepository repository) {
        this.repository = repository;
    }
}
