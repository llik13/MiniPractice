package com.example.demo.controller;

import com.example.demo.entity.Pet;

import com.example.demo.service.PetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        super();
        this.petService = petService;
    }

    @GetMapping("/pets")
    public String listPets(Model model){
        model.addAttribute("pets", petService.getAllPets());
        return "pets";
    }

    @GetMapping("/pets/new")
    public String createPetForm(Model model) {
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        return "create_pet";

    }

    @PostMapping("/pets")
    public String savePet(@Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "create_pet";
        }
        petService.savePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String editPetForm(@PathVariable Long id, Model model){
        model.addAttribute("pet", petService.getPetById(id));
        return "edit_pets";
    }

    @PostMapping("/pets/{id}")
    public String updatePet(@PathVariable Long id, @Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return "edit_pets";
        }
        Pet existingPet = petService.getPetById(id);
        existingPet.setOrganization(pet.getOrganization());
        existingPet.setKind(pet.getKind());
        existingPet.setAge(pet.getAge());
        existingPet.setSex(pet.getSex());
        petService.updatePet(existingPet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/{id}")
    public String deleteStudent(@PathVariable Long id){
        petService.deletePet(id);
        return "redirect:/pets";
    }
}
