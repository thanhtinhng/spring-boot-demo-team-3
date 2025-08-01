package org.example.service;

import org.example.models.Student;
import org.example.models.StudentSubject;
import org.example.models.StudentSubjectId;
import org.example.models.Subject;
import org.example.repository.StudentRepository;
import org.example.repository.StudentSubjectRepository;
import org.example.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentSubjectServiceImpl(StudentSubjectRepository studentSubjectRepository, StudentRepository studentRepository,  SubjectRepository subjectRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<StudentSubject> getAll() {
        return studentSubjectRepository.findAll();
    }

    @Override
    public List<StudentSubject> getByStudentId(Long studentId) {
        return studentSubjectRepository.findByStudentId(studentId);
    }

    @Override
    public StudentSubject add(StudentSubject studentSubject) {
        Student student = studentRepository.findById(studentSubject.getId().getStudentId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy student"));
        Subject subject = subjectRepository.findById(studentSubject.getId().getSubjectId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy subject"));

        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        return studentSubjectRepository.save(studentSubject);
    }

    @Override
    public void delete(Long studentId, Long subjectId) {
        StudentSubjectId id = new StudentSubjectId();
        id.setStudentId(studentId);
        id.setSubjectId(subjectId);
        studentSubjectRepository.deleteById(id);
    }

    @Override
    public StudentSubject updateScore(StudentSubjectId  id, StudentSubject updated) {
        StudentSubject existing = studentSubjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        existing.setScore(updated.getScore());

        return studentSubjectRepository.save(existing);
    }

}
