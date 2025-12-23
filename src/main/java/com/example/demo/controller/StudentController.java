package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Add a new student
    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student saved = service.addStudent(student);
        return ResponseEntity.ok(saved);
    }

    // Get list of all students
    @GetMapping
    public ResponseEntity<List<Student>> list() {
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get a single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = service.findById(id);
        return ResponseEntity.ok(student);
    }
}
