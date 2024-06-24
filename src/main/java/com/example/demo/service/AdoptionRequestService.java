package com.example.demo.service;


import com.example.demo.entity.AdoptionRequest;
import java.util.List;
import java.util.Optional;

public interface AdoptionRequestService {
    List<AdoptionRequest> getAllAdoptionRequests();
    Optional<AdoptionRequest> getAdoptionRequestById(Long id);
    AdoptionRequest createAdoptionRequest(AdoptionRequest adoptionRequest);
    AdoptionRequest updateAdoptionRequest(Long id, AdoptionRequest adoptionRequest);
    void deleteAdoptionRequest(Long id);
}