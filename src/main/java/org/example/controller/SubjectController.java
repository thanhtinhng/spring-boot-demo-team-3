package org.example.controller;

import org.example.models.Subject;
import org.example.repository.SubjectRepository;
import org.example.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;

    public SubjectController(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }

    @GetMapping
    public Iterable<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public Subject create(@RequestBody Subject subject) {
        return subjectService.add(subject);
    }
}
