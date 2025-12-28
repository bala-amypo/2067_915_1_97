public class TemplateServiceImpl implements TemplateService {

    private final CertificateTemplateRepository repo;

    public TemplateServiceImpl(CertificateTemplateRepository repo) {
        this.repo = repo;
    }

    public CertificateTemplate addTemplate(CertificateTemplate t) {
        if (repo.findByTemplateName(t.getTemplateName()).isPresent()) {
            throw new RuntimeException("Template name exists");
        }
        return repo.save(t);
    }

    public List<CertificateTemplate> getAllTemplates() {
        return repo.findAll();
    }

    public CertificateTemplate findById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
