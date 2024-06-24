package com.example.demo.controller;

import com.example.demo.entity.AdoptionRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Pet;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.reprository.UserRepository;
import com.example.demo.reprository.PetRepository;

import com.example.demo.service.PetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/adoption-requests")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;
    private final UserService userService;
    private final PetService petService;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService,
                                     UserService userService, PetService petService) {
        this.adoptionRequestService = adoptionRequestService;
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping
    public String listAdoptionRequests(Model model) {
        model.addAttribute("adoptionRequests", adoptionRequestService.getAllAdoptionRequests());
        return "adoption-requests";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("adoptionRequest", new AdoptionRequest());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("pets", petService.getAllPets());
        return "create-adoption-request";
    }

    @PostMapping("/create")
    public String createAdoptionRequest(@Valid @ModelAttribute AdoptionRequest adoptionRequest,
                                        BindingResult result, Model model) {
        adoptionRequest.setRequestDate(LocalDateTime.now());
        adoptionRequestService.createAdoptionRequest(adoptionRequest);
        return "redirect:/adoption-requests";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Optional<AdoptionRequest> adoptionRequest = adoptionRequestService.getAdoptionRequestById(id);
        model.addAttribute("adoptionRequest", adoptionRequest);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("pets", petService.getAllPets());
        return "update-adoption-request";
    }

    @PostMapping("/update/{id}")
    public String updateAdoptionRequest(@PathVariable("id") long id, @Valid AdoptionRequest adoptionRequest,
                                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            adoptionRequest.setId(id);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("pets", petService.getAllPets());
            return "update-adoption-request";
        }
        adoptionRequest.setRequestDate(LocalDateTime.now());
        adoptionRequestService.updateAdoptionRequest(id, adoptionRequest);
        return "redirect:/adoption-requests";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdoptionRequest(@PathVariable("id") long id) {
        adoptionRequestService.deleteAdoptionRequest(id);
        return "redirect:/adoption-requests";
    }
}