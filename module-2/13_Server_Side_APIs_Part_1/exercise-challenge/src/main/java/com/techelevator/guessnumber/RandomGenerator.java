package com.techelevator.guessnumber;

import java.util.Random;

public class RandomGenerator {
    public static int getRandom(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }
}
