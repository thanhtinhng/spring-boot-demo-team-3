package org.example.service;

import org.example.models.Subject;
import org.example.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject add(Subject subject) {
        return subjectRepository.save(subject);
    }
}
