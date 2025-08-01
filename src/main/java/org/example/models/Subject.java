package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int credit;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<StudentSubject> studentSubjects;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public int getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}

