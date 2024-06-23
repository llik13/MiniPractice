package com.example.demo.controller;

import com.example.demo.entity.Pet;

import com.example.demo.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        super();
        this.petService = petService;
    }

    //handler
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

    @PostMapping("pets")
    public String savePet(@ModelAttribute("pet") Pet pet) {
        petService.savePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String editPetForm(@PathVariable Long id, Model model){
        model.addAttribute("pet", petService.getPetById(id));
        return "edit_pets";
    }

    @PostMapping("/pets/{id}")
    public String updatePet(@PathVariable Long id, @ModelAttribute("pet") Pet pet, Model model){
        Pet existingPet = petService.getPetById(id);
        existingPet.setOrganization(pet.getOrganization());
        existingPet.setKind(pet.getKind());
        existingPet.setAge(pet.getAge());
        existingPet.setSex(pet.getSex());
        petService.updatePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/pets/{id}")
    public String deleteStudent(@PathVariable Long id){
        petService.deletePet(id);
        return "redirect:/pets";
    }
}
