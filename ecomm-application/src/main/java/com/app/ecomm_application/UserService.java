package com.app.ecomm_application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> createUser(User user) {

        userRepository.save(user);

        // userList.add(user);
        return getUser(user.getId());
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
