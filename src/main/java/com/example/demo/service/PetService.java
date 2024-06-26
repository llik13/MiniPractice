package com.example.demo.service;


import com.example.demo.entity.Pet;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet savePet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Long id);
    Page<Pet> getAllPetsPaginated(Pageable pageable);
}
