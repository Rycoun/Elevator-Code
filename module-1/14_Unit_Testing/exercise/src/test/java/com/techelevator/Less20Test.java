package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Less20Test {
    private Less20 less20;
    @Before
    public void set() {
        less20 = new Less20();

    }
    @Test
    public void one_less_returns_true() {
        int a = 19;
        boolean result = less20.isLessThanMultipleOf20(a);

        Assert.assertTrue(result);
    }
    @Test
    public void two_less_returns_true() {
        int a = 18;
        boolean result = less20.isLessThanMultipleOf20(a);

        Assert.assertTrue(result);
    }
    @Test
    public void zero_returns_true() {
        int a = 0;
        boolean result = less20.isLessThanMultipleOf20(a);

        Assert.assertFalse(result);
    }
    @Test
    public void Multiple_of_20_returns_false() {
        int a = 40;
        boolean result = less20.isLessThanMultipleOf20(a);

        Assert.assertFalse(result);
    }



}
