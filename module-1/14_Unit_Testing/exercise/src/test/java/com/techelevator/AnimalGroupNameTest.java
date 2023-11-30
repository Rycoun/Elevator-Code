package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalGroupNameTest {

    private AnimalGroupName animalGroupName;

    @Before
    public void set() {
        animalGroupName = new AnimalGroupName();
    }

    @Test
    public void getHerd_valid_name_returned() {
        String[] validName = {"giraffe", "Rhino", "dog",};

        String[] result = new String[validName.length];
        for (int i = 0; i < validName.length; i++) {
            result[i] = animalGroupName.getHerd(validName[i]);

        }

        Assert.assertEquals("Tower", result[0]);
        Assert.assertEquals("Crash", result[1]);
        Assert.assertEquals("Pack", result[2]);


    }

    @Test
    public void get_heard_returns_unknown() {
        String unknownAnimal = "walrus";

        String aniGroup = animalGroupName.getHerd(unknownAnimal);
        Assert.assertEquals("unknown", aniGroup);
    }

    @Test
    public void get_heard_returns_empty() {

        String empty = "";
        String aniGroup = animalGroupName.getHerd(empty);
        Assert.assertEquals("unknown", aniGroup);


    }
}


