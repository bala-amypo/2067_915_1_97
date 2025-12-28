public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(Student s) {
        if (repo.findByEmail(s.getEmail()).isPresent()
                || repo.findByRollNumber(s.getRollNumber()).isPresent()) {
            throw new RuntimeException("Student email exists");
        }
        return repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
