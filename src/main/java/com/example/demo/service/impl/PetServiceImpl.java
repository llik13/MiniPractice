package com.example.demo.service.impl;

import com.example.demo.entity.Pet;
import com.example.demo.reprository.PetRepository;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + id));
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            return petRepository.save(pet);
        }
        throw new IllegalArgumentException("Invalid pet Id:" + pet.getId());
    }

    @Override
    public void deletePet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid pet Id:" + id);
        }
    }

    @Override
    public Page<Pet> getAllPetsPaginated(Pageable pageable) {
        return petRepository.findAll(pageable);
    }
}