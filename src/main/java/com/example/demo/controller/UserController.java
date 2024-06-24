package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/users")
    public String listStudent(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String createStudentForm(Model model) {
        User student = new User();
        model.addAttribute("user", student);
        return "create_user";
    }

    @PostMapping("/users")
    public String saveStudent(@Valid @ModelAttribute("user") User student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "create_user";
        }
        userService.saveUser(student);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("user") User user, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "edit_user";
        }
        User existingUser = userService.getUserById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        userService.updateUser(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteStudent(@PathVariable Long id){
        userService.deleteUserById(id);
       return "redirect:/users";
    }
}
