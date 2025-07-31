package org.example.service;

import org.example.models.StudentSubject;
import org.example.models.StudentSubjectId;

import java.util.List;

public interface StudentSubjectService {
    List<StudentSubject> getAll();

    List<StudentSubject> getByStudentId(Long studentId);

    StudentSubject add(StudentSubject studentSubject);

    void delete(Long studentId, Long subjectId);

    StudentSubject updateScore(StudentSubjectId id, StudentSubject updated);

}
