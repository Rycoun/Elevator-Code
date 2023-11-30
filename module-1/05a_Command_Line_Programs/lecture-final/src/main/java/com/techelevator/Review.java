package com.techelevator;



public class Review {

    public static void main(String[] args) {

        String name = "Walt";

        java.lang.String nameAgain = "Walt";

        int[] nums = { 1, 5, 10, 30 };
        //             0  1   2   3

        int[] newNums = new int[nums.length - 1];
        //  [0, 0, 0]
        //   0  1  2

        for(int i = 1; i < nums.length; i++) {
            newNums[i-1] = nums[i];
        }

        for(int i = 0; i < newNums.length; i++) {
            newNums[i] = nums[i+1];
        }

        int i = 0;
        while (i < newNums.length) {
            newNums[i] = nums[i+1];
            i++;
        }


        String dayOfWeek = "M";

        switch (dayOfWeek) {
            case "M":
            case "T":
                // if it's monday or tuesday
                break;
            default:
        }


    }

}
