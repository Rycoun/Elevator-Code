package com.techelevator;

import com.techelevator.bank.MapExer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapExerTest {
    @Test
    public void robPeterToPayPaul_does_nothing_with_no_funds() {
        Map<String, Integer> statingBal = new HashMap<>();
        statingBal.put("Paul", 1_000);
        statingBal.put("Peter", 2_000);
        MapExer sut = new MapExer();

        Map<String, Integer> result = sut.robPeterToPayPaul(statingBal);

        // Assert.assertEquals(statingBal, result);
        Assert.assertEquals(2,result.size());
        Assert.assertEquals(1_000, (int)result.get("Paul"));
        Assert.assertEquals(2_000, (int)result.get("Peter"));

    }

    @Test
    public void robPeterToPayPaul_transfers_half_of_peters_money() {
        Map<String, Integer> startingBalance = new HashMap<>();
        startingBalance.put("Paul", 1_000);
        startingBalance.put("Peter",5_001);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("Paul", 3_500);
        expectedResult.put("Peter", 2_501);

        MapExer sut = new MapExer();

        Map<String, Integer> result = sut.robPeterToPayPaul(startingBalance);

        Assert.assertEquals(2,result.size());
        Assert.assertEquals(3_500, (int)result.get("Paul"));
        Assert.assertEquals(2_501, (int)result.get("Peter"));

    }






}
