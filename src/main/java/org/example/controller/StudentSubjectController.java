package org.example.controller;

import org.example.models.StudentSubject;
import org.example.models.StudentSubjectId;
import org.example.service.StudentSubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students-subjects")
public class StudentSubjectController {

    private final StudentSubjectService service;

    public StudentSubjectController(StudentSubjectService service) {
        this.service = service;
    }


    @GetMapping
    public List<StudentSubject> getAll() {
        return service.getAll();
    }

    @GetMapping("/student/{id}")
    public List<StudentSubject> getByStudent(@PathVariable("id") Long id) {
        return service.getByStudentId(id);
    }

    @PostMapping
    public StudentSubject create(@RequestBody StudentSubject studentSubject) {
        return service.add(studentSubject);
    }

    @PutMapping("/student/{studentId}/subject/{subjectId}")
    public StudentSubject update(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId,
            @RequestBody StudentSubject updated) {
        StudentSubjectId id = new StudentSubjectId();
        id.setStudentId(studentId);
        id.setSubjectId(subjectId);
        return service.updateScore(id, updated);
    }

    @DeleteMapping("/{studentId}/{subjectId}")
    public void delete(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        service.delete(studentId, subjectId);
    }
}
