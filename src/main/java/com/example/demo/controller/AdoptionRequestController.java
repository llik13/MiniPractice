package com.example.demo.controller;

import com.example.demo.entity.AdoptionRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Pet;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.reprository.UserRepository;
import com.example.demo.reprository.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/adoption-requests")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService,
                                     UserRepository userRepository, PetRepository petRepository) {
        this.adoptionRequestService = adoptionRequestService;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @GetMapping
    public String listAdoptionRequests(Model model) {
        model.addAttribute("adoptionRequests", adoptionRequestService.getAllAdoptionRequests());
        return "adoption-requests";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("adoptionRequest", new AdoptionRequest());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        return "create-adoption-request";
    }

    @PostMapping("/create")
    public String createAdoptionRequest(@Valid @ModelAttribute AdoptionRequest adoptionRequest,
                                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("pets", petRepository.findAll());
            return "create-adoption-request";
        }
        adoptionRequest.setRequestDate(LocalDateTime.now());
        adoptionRequestService.createAdoptionRequest(adoptionRequest);
        return "redirect:/adoption-requests";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        AdoptionRequest adoptionRequest = adoptionRequestService.getAdoptionRequestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid adoption request Id:" + id));
        model.addAttribute("adoptionRequest", adoptionRequest);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        return "update-adoption-request";
    }

    @PostMapping("/update/{id}")
    public String updateAdoptionRequest(@PathVariable("id") long id, @Valid AdoptionRequest adoptionRequest,
                                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            adoptionRequest.setId(id);
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("pets", petRepository.findAll());
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