package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcPetDaoTests extends BaseDaoTests {
    // Test Data
    private static final Pet PET_246 = new Pet(246, "Rover", "Dog", LocalDate.of(2011, 10, 20), "Sam Cook");

    // Class we are testing
    private JdbcPetDao sut;

    @Before
    public void setup() {
        sut = new JdbcPetDao(dataSource);
    }

    @Test
    public void getPetById_returns_pet_for_valid_id() {

        Pet pet = sut.getPetById(246);

        Assert.assertEquals(PET_246, pet);
    }

    @Test
    public void visitCount_returns_valid_result() {
        LocalDate startDate = LocalDate.of(2002,1,1);
        LocalDate endDate = LocalDate.of(2002,12,31);

        int visitCount = sut.visitCount(246, startDate, endDate);

        Assert.assertEquals(3, visitCount);


        startDate = LocalDate.of(2002,3,1);
        endDate = LocalDate.of(2002,5,30);

        visitCount = sut.visitCount(246, startDate, endDate);

        Assert.assertEquals(2, visitCount);
    }


    @Test
    public void deletePetsByType_removes_all_birds() {
        sut.deletePetsByType("Bird");

        List<Pet> allPets = sut.getAllPets();

//        int birdCount = 0;
        for (Pet pet : allPets) {
            if (pet.getType().equals("Bird")) {
//                birdCount++;

                Assert.fail("A bird still exists in pet results");
            }
        }

//        Assert.assertEquals(0, birdCount);
    }



    private void assertPetsMatch(Pet expected, Pet actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.getOwner(), actual.getOwner());
    }

}
