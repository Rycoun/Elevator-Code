package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private List<Integer> nums = new ArrayList<>(); // { 1, 2, 5, 10 }


    public Integer[] getNums() {
        // create the array that is the same size as the list
        Integer[] numsArray = new Integer[nums.size()]; // [null, null, null, null]

        return nums.toArray(numsArray);


        // loop over either one, assign from the list into each index of the array
//        for (int i = 0; i < numsArray.length; i++) {
//            numsArray[i] = nums.get(i);
//        }
//
//        return numsArray;
    }
}
