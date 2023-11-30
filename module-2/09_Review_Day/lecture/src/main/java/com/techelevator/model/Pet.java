package com.techelevator.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pet {
    private int Id;
    private String name;
    private String type;
    private LocalDate birthDate;
    private String owner;

    public Pet() {

    }


    public Pet(int id, String name, String type, LocalDate birthDate, String owner) {
        Id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Id == pet.Id && name.equals(pet.name) && Objects.equals(type, pet.type) && Objects.equals(birthDate, pet.birthDate) && Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, type, birthDate, owner);
    }
}
