package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity //JPA Entity Mapping
@Table(name = "students") // ánh xạ với bảng "student"
public class Student {
    @Id //khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng ID
    private Long id;

    @NotBlank(message = "Tên không được để trống")  // Không null và không toàn khoảng trắng
    @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 ký tự")
    private String name;

    @NotBlank
    private String email;

    @NotNull
    @Max(value = 100, message = "Tuổi tối đa là 100")
    private int age;

    @ManyToOne // nhiều sinh viên thuộc 1 lớp học
    @JoinColumn(name = "school_id")  // khóa ngoại trỏ đến bảng class
    private School school;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentSubject> studentSubjects;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classRoom=" + (school != null ? school.getName() : "null") +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

//docs validator: https://docs.jboss.org/hibernate/validator/9.0/reference/en-US/html_single/#validator-gettingstarted
