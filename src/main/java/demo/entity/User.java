package demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // tránh dùng từ khóa `user`
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ⚠️ Bắt buộc: constructor mặc định
    public User() {
    }

    // Constructor tiện dùng khi tạo mới
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters và setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
