package org.example.service;

import org.example.models.Student;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    List<Student> getAllStudents();

    Optional<Student> getStudentByEmail(String email);

    Student add(Student student);

    void delete(Long id);

    Student updateStudent(Long id, Student updatedStudent);

    //them
    List<Student> searchByNameOrEmail(String keyword);

    Student findByEmailNative(String email);

    List<Student> findByNameEndingWith(String name);

    List<Student> findByNameContaining(String name);

}