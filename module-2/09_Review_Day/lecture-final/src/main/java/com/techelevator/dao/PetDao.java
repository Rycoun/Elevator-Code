package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.time.LocalDate;
import java.util.List;

public interface PetDao {

    Pet createPet(Pet petToCreate);

    List<Pet> getAllPets();

    Pet getPetById(int petId);

    int numberOfPets(String owner, boolean useWildcard);

    int visitCount(int petId, LocalDate startDate, LocalDate endDate);

    Pet updatePet(Pet petToUpdate);

    void deletePetById(int petId);

    void deletePetsByType(String petType);
}
