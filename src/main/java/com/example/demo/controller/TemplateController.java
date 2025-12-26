@RestController
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    private TemplateService service;

    @PostMapping
    public CertificateTemplate create(@RequestBody CertificateTemplate template) {
        return service.save(template);
    }
}
