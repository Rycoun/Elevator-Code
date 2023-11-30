package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class review {

    private List<Integer> nums = new ArrayList<>(); // { 1,2,5,10}
    public Integer[] getNums() {
        Integer[] numsArry = new Integer[nums.size()];
        for (int i = 0; i < numsArry.length; i++) {
            numsArry[i] = nums.get(i);

        }
        return numsArry;

    }

}
