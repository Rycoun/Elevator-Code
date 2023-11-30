package com.techelevator;

public class Bid {
    private final String bidder;
    private final int amount;

    public Bid(String bidder, int amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public String getBidder() {
        return bidder;
    }

    public int getAmount() {
        return amount;
    }
}
