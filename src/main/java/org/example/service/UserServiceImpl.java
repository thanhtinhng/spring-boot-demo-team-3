package org.example.service;


import jakarta.transaction.Transactional;
import org.example.entity.AppUser;
import org.example.repository.userRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser updateUser(Long id, AppUser updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void adminOnlyMethod() {
        System.out.println("Admin-only logic");
    }

    @Override
    @PreAuthorize("#username == authentication.name")
    public String viewOwnProfile(String username) {
        return "Viewing profile: " + username;
    }
}
