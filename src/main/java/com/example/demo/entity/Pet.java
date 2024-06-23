package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Pet")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization", nullable = false)
    private String organization;
    @Column(name = "kind", nullable = false)
    private String kind;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "sex", nullable = false)
    private String sex;


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