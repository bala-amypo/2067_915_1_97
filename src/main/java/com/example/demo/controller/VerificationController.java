@RestController
@RequestMapping("/verify")
public class VerificationController {

    @Autowired
    private VerificationService service;

    @GetMapping
    public VerificationLog verify() {
        return service.verify();
    }
}
