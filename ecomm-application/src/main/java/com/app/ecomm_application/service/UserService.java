package com.app.ecomm_application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ecomm_application.model.User;
import com.app.ecomm_application.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> createUser(User user) {
        user.setId(null); // Ensure ID is null for new entities
        User savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }

    public Optional<User> getUser(Long id) {

        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

    public Optional<User> updateUser(Long id, User user) {
        user.setId(id);
        return createUser(user);
    }
}
