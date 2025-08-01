package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schools") // ánh xạ với bảng "schools"
public class School {
    @Id //khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Một school học có nhiều sinh viên
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true) // trong student có thuộc tính school
    private List<Student> students;

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
