package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student addStudent(Student student) {
        if (repo.findByEmail(student.getEmail()).isPresent()
            || repo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new RuntimeException("Student email exists");
        }
        return repo.save(student);
    }

    @Override
    public Student findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
