package com.techelevator;

import com.techelevator.arrays.ArrayExercise;
import org.junit.Assert;
import org.junit.Test;

public class ArrayExerciseTest {

//    @Test
//    public void reverseArray_returns_null_given_null() {
//        int[] inputArray = null;
//        ArrayExercise sut = new ArrayExercise();
//
//        int[] reversedArray = sut.reverseArray(inputArray);
//
//        Assert.assertNull(reversedArray);
//    }

    @Test
    public void reverseArray_returns_empty_given_empty() {
        int[] inputArray = {};
        ArrayExercise sut = new ArrayExercise();

        int[] reversedArray = sut.reverseArray(inputArray);

        Assert.assertEquals(0, reversedArray.length);
    }

    @Test
    public void reverseArray_returns_1_2_3_given_3_2_1() {
        int[] inputArray = { 3, 2, 1 };
        int[] expectedResult = { 1, 2, 3 };
        ArrayExercise sut = new ArrayExercise();

        int[] reversedArray = sut.reverseArray(inputArray);

        Assert.assertArrayEquals(expectedResult, reversedArray);
    }
}
