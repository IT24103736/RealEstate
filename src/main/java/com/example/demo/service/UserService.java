package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.FileUtils.UserFileUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final String FILE_PATH = "data/realestate_users.txt";

    public List<User> getAllUsers() {
        return UserFileUtils.readUsersFromFile(FILE_PATH);
    }

    public void addUser(User user) {
        List<User> users = getAllUsers();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
        }

        String id = "U" + UUID.randomUUID().toString().substring(0, 8);
        user.setId(id);
        users.add(user);
        UserFileUtils.writeUsersToFile(FILE_PATH, users);
    }

    public User authenticate(String email, String password) {
        for (User user : getAllUsers()) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(String id, User updatedUser) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(id)) {
                if (updatedUser.getName() != null) user.setName(updatedUser.getName());
                if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
                if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());
                if (updatedUser.getRole() != null) user.setRole(updatedUser.getRole());
                break;
            }
        }
        UserFileUtils.writeUsersToFile(FILE_PATH, users);
    }

    public void deleteUser(String id) {
        List<User> users = getAllUsers();
        users.removeIf(user -> user.getId().equals(id));
        UserFileUtils.writeUsersToFile(FILE_PATH, users);
    }
}
