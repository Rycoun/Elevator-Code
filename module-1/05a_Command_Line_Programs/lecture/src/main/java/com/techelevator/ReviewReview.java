package com.techelevator;

import java.util.Scanner;

public class ReviewReview {
    public static void main(String[] args) {
        int[] nums = { 1, 5, 10, 30 };
        int[] newNums = new int[nums.length -1];
        for(int i = 1; i < nums.length; i++) {
            newNums[i-1] = nums[i];
        }

    }


}
