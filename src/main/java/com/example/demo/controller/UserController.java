package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService studentService){
        super();
        this.userService = studentService;
    }

    //handler
    @GetMapping("/users")
    public String listStudent(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        User student = new User();
        model.addAttribute("user", student);
        return "create_user";

    }

    @PostMapping("/users")
    public String saveStudent(@ModelAttribute("user") User student) {
        userService.saveUser(student);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") User student, Model model){
        User existingStudent = userService.getUserById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        userService.updateUser(existingStudent);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteStudent(@PathVariable Long id){
        userService.deleteUserById(id);
       return "redirect:/users";
    }
}
