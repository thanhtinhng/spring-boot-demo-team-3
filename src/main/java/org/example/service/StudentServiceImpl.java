package org.example.service;

import org.example.models.School;
import org.example.models.Student;
import org.example.repository.SchoolRepository;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tồn tại sinh viên với id: " + id));

        if (updatedStudent.getName() != null) {
            existing.setName(updatedStudent.getName());
        }

        if (updatedStudent.getEmail() != null) {
            existing.setEmail(updatedStudent.getEmail());
        }

        if (updatedStudent.getAge() != 0) {
            existing.setAge(updatedStudent.getAge());
        }

        if (updatedStudent.getSchool() != null && updatedStudent.getSchool().getId() != null) {
            School school = schoolRepository.findById(updatedStudent.getSchool().getId())
                    .orElseThrow(() -> new RuntimeException("Không tồn tại trường học với id: " + updatedStudent.getSchool().getId()));
            existing.setSchool(school);
        }

        return studentRepository.save(existing);
    }

    //them
    @Override
    public List<Student> searchByNameOrEmail(String keyword) {
        return studentRepository.searchByNameOrEmail(keyword);
    }

    @Override
    public Student findByEmailNative(String email) {
        return studentRepository.findByEmailNative(email);
    }

    @Override
    public List<Student> findByNameEndingWith(String name) {
        return studentRepository.findByNameEndingWithIgnoreCase(name);
    }

    @Override
    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

}
