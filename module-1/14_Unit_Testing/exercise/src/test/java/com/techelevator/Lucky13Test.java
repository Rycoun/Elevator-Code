package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Lucky13Test {
    private Lucky13 lucky13;
    @Before
    public void  set() {
        lucky13 = new Lucky13();

    }
    @Test
    public void has_one_returns_false() {
        int[] numbers = {1,2,3};
        boolean total = lucky13.getLucky(numbers);
        Assert.assertFalse(total);
    }
    @Test
    public void no_one_or_three_returns_true() {
        int[] numbers = {0,2,4};
        boolean total = lucky13.getLucky(numbers);
        Assert.assertTrue(total);
    }
    @Test
    public void has_three_returns_false() {
        int[] numbers = {1,2,3};
        boolean total = lucky13.getLucky(numbers);
        Assert.assertFalse(total);
    }
    @Test
    public void if_null_returns_true() {
        int[] numbers = null;
        boolean total = lucky13.getLucky(numbers);
        Assert.assertTrue(total);
    }
}
