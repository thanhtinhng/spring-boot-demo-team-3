package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student_subject")
public class StudentSubject {

    @EmbeddedId
    private StudentSubjectId id = new StudentSubjectId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Double score;

    @Override
    public String toString() {
        return  "StudentSubject{" +
                "student Id=" + id.getStudentId() +
                ", name='" + student.getName() + '\'' +
                ", subject Id=" + id.getSubjectId() +
                ", subject name='" + subject.getName() + '\'' +
                ", score=" + score +
                '}';
    }

    public StudentSubjectId getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Student getStudent() {
        return student;
    }

    public Double getScore() {
        return score;
    }

    public void setId(StudentSubjectId id) {
        this.id = id;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
