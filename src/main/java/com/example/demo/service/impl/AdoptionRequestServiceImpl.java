package com.example.demo.service.impl;

import com.example.demo.entity.AdoptionRequest;
import com.example.demo.reprository.AdoptionRequestRepository;
import com.example.demo.service.AdoptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    @Override
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    @Override
    public Optional<AdoptionRequest> getAdoptionRequestById(Long id) {
        return adoptionRequestRepository.findById(id);
    }

    @Override
    public AdoptionRequest createAdoptionRequest(AdoptionRequest adoptionRequest) {
        return adoptionRequestRepository.save(adoptionRequest);
    }

    @Override
    public AdoptionRequest updateAdoptionRequest(Long id, AdoptionRequest adoptionRequest) {
        if (adoptionRequestRepository.existsById(id)) {
            adoptionRequest.setId(id);
            return adoptionRequestRepository.save(adoptionRequest);
        }
        throw new IllegalArgumentException("Invalid adoption request Id:" + id);
    }

    @Override
    public void deleteAdoptionRequest(Long id) {
        if (adoptionRequestRepository.existsById(id)) {
            adoptionRequestRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid adoption request Id:" + id);
        }
    }
}