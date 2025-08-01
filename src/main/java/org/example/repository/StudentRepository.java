package org.example.repository;

import org.example.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findByNameEndingWithIgnoreCase(String name);

    List<Student> findByNameContainingIgnoreCase(String name);

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:keyword% OR s.email LIKE %:keyword%")
    List<Student> searchByNameOrEmail(@Param("keyword") String keyword);

    // Native SQL
    @Query(value = "SELECT * FROM students WHERE email = ?1", nativeQuery = true)
    Student findByEmailNative(String email);

}