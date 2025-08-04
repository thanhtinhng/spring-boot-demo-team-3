package org.example.controller;

import org.example.models.StudentSubject;
import org.example.models.StudentSubjectId;
import org.example.service.StudentSubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentSubjectController {

    private final StudentSubjectService service;

    public StudentSubjectController(StudentSubjectService service) {
        this.service = service;
    }

    @GetMapping("/api/students-subjects")
    public List<StudentSubject> getAll() {
        return service.getAll();
    }

    @GetMapping("/api/students/{id}")
    public List<StudentSubject> getByStudent(@PathVariable("id") Long id) {
        return service.getByStudentId(id);
    }

    @PostMapping("/api/students/{id}")
    public StudentSubject create(
            @RequestBody StudentSubject studentSubject, //body truyền "id": {"subjectId": 2}, "score": 10}
            @PathVariable("id") Long studentId) {
        studentSubject.getId().setStudentId(studentId);
        return service.add(studentSubject);
    }

    @PutMapping("/api/students/{studentId}/subjects/{subjectId}")
    public StudentSubject update(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId,
            @RequestBody StudentSubject updated) { //body truyền score
        StudentSubjectId id = new StudentSubjectId();
        id.setStudentId(studentId);
        id.setSubjectId(subjectId);
        return service.updateScore(id, updated);
    }

    @DeleteMapping("/api/students/{studentId}/subjects/{subjectId}")
    public void delete(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        service.delete(studentId, subjectId);
    }
}
