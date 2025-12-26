@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @PostMapping
    public Certificate create(@RequestBody Certificate certificate) {
        return service.save(certificate);
    }
}
