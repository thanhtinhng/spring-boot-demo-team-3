package org.example.controller;

import org.example.exception.ResourceNotFoundExceptionForGlobalEx;
import org.example.exception.ResourceNotFoundExceptionForLocalEx;
import org.example.models.Student;
import org.example.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/local-exception")
    public String exampleException() {
        throw new ResourceNotFoundExceptionForLocalEx("Không tìm thấy tài nguyên.");
    }

    @ExceptionHandler(ResourceNotFoundExceptionForLocalEx.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundExceptionForLocalEx ex) {
        // Trả về mã lỗi 404 và thông báo lỗi
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @GetMapping("/global-exception")
    public String getStudentById(@RequestParam("id") int id) {
        if (id == 0) {
            throw new ResourceNotFoundExceptionForGlobalEx("ID không được phép là 0");
        }
        return "Tìm thấy sinh viên với id=" + id + studentService.findById((long) id);
    }

    @GetMapping("/global-exception-fallback")
    public String simulateUnexpectedError() {
        throw new RuntimeException("Lỗi không xác định ở Server...");
    }

    @GetMapping("/{username}")
    public List<Student> getUserByUsername(@PathVariable("username") String username) {
        if (username.equals("admin")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bạn không được phép thực hiện yêu cầu này.");
        }
        return studentService.getUserByUsername(username);
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
