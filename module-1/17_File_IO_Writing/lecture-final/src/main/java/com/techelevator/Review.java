package com.techelevator;

public class Review {


    public boolean placeBid(int bidAmount) throws BidException {
        if (bidAmount < 0) {
            BidException ex = new BidException();
            throw ex;
        }

        // is the bid amount the highest bid, return true if so
        return true;

    }

    public void intermediate() throws BidException {

        placeBid(-1);
    }


    public static void main(String[] args) {

        Review review = new Review();

        try {
            review.intermediate();
        } catch (BidException e) {

        }


    }
}
