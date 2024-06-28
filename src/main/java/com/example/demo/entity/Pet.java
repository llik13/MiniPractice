package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;


@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization", nullable = false)
    @NotBlank(message = "Organization is mandatory")
    @Size(min = 2, max = 100, message = "Organization must consist of 2 to 100 symbols")
    private String organization;

    @Column(name = "kind", nullable = false)
    @NotBlank(message = "Kind is mandatory")
    @Size(min = 2, max = 30, message = "Kind must consist of 2 to 30 symbols")
    private String kind;

    @Column(name = "age", nullable = false)
    @Range(min = 0, max = 25, message = "Age must be between 0 to 25")
    private int age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AdoptionRequest adoptionRequest;


    public Pet() {
    }

    public Pet(String organization, String kind, int age, String sex) {
        this.organization = organization;
        this.kind = kind;
        this.age = age;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}