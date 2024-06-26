package com.example.demo.controller;

import com.example.demo.entity.AdoptionRequest;
import com.example.demo.entity.Pet;
import com.example.demo.entity.User;
import com.example.demo.service.AdoptionRequestService;

import com.example.demo.service.PetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
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

    @GetMapping("/adoption-requests")
    public String listAdoptionRequests(Model model) {
        model.addAttribute("adoptionRequests", adoptionRequestService.getAllAdoptionRequests());
        return "adoption-requests";
    }

    @GetMapping("/adoption-requests/create")
    public String showCreateForm(Model model) {
        model.addAttribute("adoptionRequest", new AdoptionRequest());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("pets", petService.getAllPets());
        return "create-adoption-request";
    }

    @PostMapping("/adoption-requests")
    public String createAdoptionRequest(@RequestParam("userId") Long userId, @RequestParam("petId") Long petId, Model model) {
        User user = userService.getUserById(userId);
        Pet pet = petService.getPetById(petId);
        if (user == null || pet == null) {
            return "create-adoption-request";
        }

        AdoptionRequest adoptionRequest = new AdoptionRequest();
        adoptionRequest.setUser(user);
        adoptionRequest.setPet(pet);
        LocalDate currentDate = LocalDate.now();
        LocalDateTime requestDate = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), 0, 0);
        adoptionRequest.setRequestDate(requestDate);

        adoptionRequestService.createAdoptionRequest(adoptionRequest);

        return "redirect:/adoption-requests";
    }

    @GetMapping("/adoption-requests/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Optional<AdoptionRequest> adoptionRequestOptional = adoptionRequestService.getAdoptionRequestById(id);
        AdoptionRequest adoptionRequest = adoptionRequestOptional.orElseThrow();
        model.addAttribute("adoptionRequest", adoptionRequest);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("pets", petService.getAllPets());
        return "update-adoption-request";
    }

    @PostMapping("/adoption-requests/{id}")
    public String updateAdoptionRequest(@PathVariable("id") long id, AdoptionRequest adoptionRequest, Model model) {
        adoptionRequest.setRequestDate(LocalDateTime.now());
        adoptionRequestService.updateAdoptionRequest(id, adoptionRequest);
        return "redirect:/adoption-requests";
    }

    @GetMapping("/adoption-requests/{id}")
    public String deleteAdoptionRequest(@PathVariable("id") long id) {
        adoptionRequestService.deleteAdoptionRequest(id);
        return "redirect:/adoption-requests";
    }
}