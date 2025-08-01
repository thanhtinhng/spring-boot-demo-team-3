package org.example.repository;

import org.example.models.StudentSubject;
import org.example.models.StudentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {
    List<StudentSubject> findByStudentId(Long studentId);

}
