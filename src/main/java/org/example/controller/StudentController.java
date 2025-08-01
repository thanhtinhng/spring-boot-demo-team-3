package org.example.controller;

import org.example.models.Student;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/email")
    public Optional<Student> getByEmail(@RequestParam("email") String email) {
        return studentService.getStudentByEmail(email);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.add(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    //them
    @GetMapping("/search")
    public List<Student> searchByNameOrEmail(@RequestParam("keyword") String keyword) {
        return studentService.searchByNameOrEmail(keyword);
    }

    @GetMapping("/email-native")
    public Student findByEmailNative(@RequestParam("email") String email) {
        return studentService.findByEmailNative(email);
    }

    @GetMapping("/name-end")
    public List<Student> getByNameEndingWith(@RequestParam("keyword") String nameEnding) {
        return studentService.findByNameEndingWith(nameEnding);
    }

    @GetMapping("/search-name-contain")
    public List<Student> findByNameContaining(@RequestParam("keyword") String nameContaining) {
        return studentService.findByNameContaining(nameContaining);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

}
