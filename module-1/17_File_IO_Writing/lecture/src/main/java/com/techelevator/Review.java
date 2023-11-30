package com.techelevator;

public class Review {

    public boolean placeBid(int amount) {
        if (amount < 0 ) {
            throw new IllegalArgumentException("Bid amount must be positive");
        }

        return true;
    }

    public void intermediate() {
        placeBid(-1);
    }


    public static void main(String[] args) {

    }
}
