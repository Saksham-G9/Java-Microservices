package com.app.ecomm_application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();

    public List<User> getAllUsers() {
        return userList;
    }

    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    public User getUser(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User deleteUser(int id) {
        User userToDelete = getUser(id);
        if (userToDelete != null) {
            userList.remove(userToDelete);
            return userToDelete;
        }
        return null;
    }
}
