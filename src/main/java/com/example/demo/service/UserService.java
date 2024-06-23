package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User student);
    User getUserById(Long id);
    User updateUser(User student);
    void deleteUserById(Long id);
}
