package com.techelevator.arrays;

public class ArrayExercise {

    public int[] reverseArray(int[] arrayToReverse) {
        if (arrayToReverse == null) {
            return null;
        }

        int[] reversedArray = new int[arrayToReverse.length];

        for (int i = arrayToReverse.length-1, j = 0; i >= 0; i--, j++) {
            reversedArray[j] = arrayToReverse[i];
        }

        return reversedArray;
    }
}
