package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxEnd3Test {
    private MaxEnd3 maxEnd3;
    @Before
    public void set(){
        maxEnd3 = new MaxEnd3();
    }
    @Test
    public void array_first_larger_returns_array_first() {

        int[] numbers = {3, 2, 1};
        int[] expecting = {3, 3, 3};
        int[] result = maxEnd3.makeArray(numbers);
        Assert.assertEquals(expecting,numbers);
    }
    @Test
    public void array_last_larger_returns_array_last() {
        int[] numbers = {1, 2, 3};
        int[] expecting = {3, 3, 3};


        int[] result = maxEnd3.makeArray(numbers);


        Assert.assertArrayEquals(expecting, result);
    }
    @Test
    public void array_all_equal_returns_equal() {
        // Arrange
        int[] numbers = {2, 2, 2};
        int[] expecting = {2, 2, 2};

        // Act
        int[] result = maxEnd3.makeArray(numbers);

        // Assert
        Assert.assertArrayEquals(expecting, result);
    }
    @Test
    public void array_null_returns_Null() {
        // Arrange
        int[] numbers = null;

        int[] result = maxEnd3.makeArray(numbers);

        Assert.assertNull(result);
    }

    }
