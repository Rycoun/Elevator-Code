package com.techelevator;

import com.techelevator.bank.MapExercises;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapExercisesTest {

    @Test
    public void robPeterToPayPaul_does_nothing_when_peter_has_insufficient_funds() {
        // arrange
        Map<String, Integer> startingBalances = new HashMap<>();
        startingBalances.put("Paul", 1_000);
        startingBalances.put("Peter", 2_000);

        MapExercises sut = new MapExercises();

        // act
        Map<String, Integer> result = sut.robPeterToPayPaul(startingBalances);

        // assert
//        Assert.assertEquals(startingBalances, result);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(1_000, (int) result.get("Paul"));
        Assert.assertEquals(2_000, (int) result.get("Peter"));

    }

    /*
    Option 1 for verifying a map

    Create a map that you expect the result to look like
    Assert.assertEquals using each map (the one you created and the one returned from the method called upon in Act)
     */
    @Test
    public void robPeterToPayPaul_transfers_half_of_peters_money_option1() {
        // arrange
        Map<String, Integer> startingBalances = new HashMap<>();
        startingBalances.put("Paul", 1_000);
        startingBalances.put("Peter", 5_001);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("Paul", 3_500);
        expectedResult.put("Peter", 2_501);

        MapExercises sut = new MapExercises();

        // act
        Map<String, Integer> result = sut.robPeterToPayPaul(startingBalances);

        // assert
        Assert.assertEquals(expectedResult, result);
    }

    /*
     Option 2 for verifying a map

     Assert each value that you expect to be in the map and the map's size
  */
    @Test
    public void robPeterToPayPaul_transfers_half_of_peters_money_option2() {
        // arrange
        Map<String, Integer> startingBalances = new HashMap<>();
        startingBalances.put("Paul", 1_000);
        startingBalances.put("Peter", 5_001);

        MapExercises sut = new MapExercises();

        // act
        Map<String, Integer> result = sut.robPeterToPayPaul(startingBalances);

        // assert
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(3_500, (int) result.get("Paul"));
        Assert.assertEquals(2_501, (int) result.get("Peter"));
    }

}
