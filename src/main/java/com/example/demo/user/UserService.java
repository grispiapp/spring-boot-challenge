package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User createUser(User user) {
        return userRepository.save(user);
    }

    User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
