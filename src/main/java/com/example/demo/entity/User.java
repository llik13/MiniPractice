package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 30, message = "First name must consist of 2 to 30 symboles")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Second name is mandatory")
    @Size(min = 2, max = 30, message = "First name must consist of 2 to 30 symboles")
    private String lastName;

    @Column(name ="email", nullable = false)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter correct email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AdoptionRequest> adoptionRequests;

    public User(){}

    public User(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
