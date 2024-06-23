package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.reprository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;


    public UserServiceImp(UserRepository studentRepository) {
        super();
        this.userRepository = studentRepository;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User student) {
        return userRepository.save(student);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User student) {
        return userRepository.save(student);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
