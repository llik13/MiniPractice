package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_request")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is mandatory")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @NotNull(message = "Pet is mandatory")
    private Pet pet;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    public AdoptionRequest() {
    }

    public AdoptionRequest(User user, Pet pet, LocalDateTime requestDate) {
        this.user = user;
        this.pet = pet;
        this.requestDate = requestDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}