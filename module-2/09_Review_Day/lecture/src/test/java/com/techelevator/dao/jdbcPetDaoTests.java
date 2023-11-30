package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class jdbcPetDaoTests extends BaseDaoTests{
    private static final Pet PET_246 = new Pet(246, "Rover", "Dog", LocalDate.of(2011, 10, 20), "Sam Cook");

    private jdbcPetDao sut;

    @Before
    public void setup() {
        sut = new jdbcPetDao(dataSource);
    }


    @Test
    public void getPetByID_returns_pat_for_valid_id() {
        Pet pet = sut.getPetById(246);
        assertPetsMatch(PET_246, pet);
    }

    @Test
    public void visitCount_returns_valid_result() {
        sut.visitCount(246, LocalDate.of(2002, 1,1), LocalDate.of(2002, 12, 31);

        int visitCount = sut.visitCount(246, startDate, End);

        Assert.assertEquals(3, visitCount);
    }

    @Test
    public void deletesByType_all_birds() {
        sut.deletePetsByType("Bird");

        List<Pet> allPets = sut.getAllPets();

        int birdCount = 0;
        for (Pet pet : allPets) {
            if(pet.getType().equals("Bird")) {
                birdCount++;
            }
        }
        Assert.assertEquals(0, birdCount);





    }


    private void assertPetsMatch(Pet expected, Pet actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.getOwner(), actual.getOwner());
    }







}
