package com.example.demo.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
