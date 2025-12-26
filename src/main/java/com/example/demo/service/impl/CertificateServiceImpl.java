@Service
public class CertificateServiceImpl implements CertificateService {
    // ... existing constructor

    @Override
    public List<Certificate> findByStudentId(Long studentId) { // Fixed: method must be implemented
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return certificateRepository.findByStudent(student);
    }
}