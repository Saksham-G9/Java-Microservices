package com.app.ecomm_application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();

    public List<User> getAllUsers() {
        return userList;
    }

    public Optional<User> createUser(User user) {
        user.setId(userList.size() + 1L);
        userList.add(user);
        return getUser(user.getId());
    }

    public Optional<User> getUser(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> deleteUser(Long id) {
        Optional<User> userOpt = getUser(id);
        userOpt.ifPresent(user -> userList.remove(user));
        return userOpt;
    }

    public Optional<User> updateUser(Long id, User user) {
        Optional<User> userOpt = getUser(id);
        userOpt.ifPresent(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
        });
        return userOpt;
    }
}
