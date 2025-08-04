package org.example.controller;

import org.example.entity.AppUser;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUser user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> update(@PathVariable Long id, @RequestBody AppUser user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public String myProfile(@RequestParam String username) {
        return userService.viewOwnProfile(username);
    }

//    @GetMapping("/public")
//    @ResponseBody
//    public String publicPage() {
//        return "Trang công khai - ai cũng xem được.";
//    }

    @GetMapping("/info")
    @ResponseBody
    public String userPage() {
        return "Trang của USER hoặc ADMIN.";
    }

//    @GetMapping("/admin/info")
//    @ResponseBody
//    public String adminPage() {
//        return "Trang ADMIN.";
//    }
//
//    @GetMapping("/home")
//    @ResponseBody
//    public String home() {
//        return "Trang HOME sau khi đăng nhập.";
//    }

}

