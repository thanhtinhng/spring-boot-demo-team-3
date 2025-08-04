package org.example.service;


import org.example.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<AppUser> findAll();
    Optional<AppUser> findById(Long id);
    AppUser createUser(AppUser user);
    AppUser updateUser(Long id, AppUser updatedUser);
    void deleteUser(Long id);

    // Các method bảo mật riêng
    void adminOnlyMethod();
    String viewOwnProfile(String username);
}
