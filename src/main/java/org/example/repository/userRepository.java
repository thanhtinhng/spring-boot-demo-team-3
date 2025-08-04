package org.example.repository;

import org.example.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
