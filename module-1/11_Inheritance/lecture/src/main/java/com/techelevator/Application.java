package com.techelevator;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {

        // Create a new general auction
        System.out.println("Starting a general auction");
        System.out.println("-----------------");
        LocalDateTime endDateTime = LocalDateTime.of(2023, 10, 1, 13, 0);

        Auction generalAuction = new Auction("Tech Elevator t-shirt", endDateTime);

        generalAuction.placeBid(new Bid("Josh", 1));
        generalAuction.placeBid(new Bid("Fonz", 23));
        generalAuction.placeBid(new Bid("Rick Astley", 13));
        //....
        //....
        // This might go on until the auction runs out of time or hits a max # of bids
        System.out.println("Current high bid by " + generalAuction.getCurrentWinningBid().getBidder());

        ReserveAuction reserveAuction = new ReserveAuction("Tech Elevator backpack", 25);
        reserveAuction.placeBid(new Bid("Josh", 2));
        reserveAuction.placeBid(new Bid("Josh", 25));
        reserveAuction.placeBid(new Bid("Fonz", 35));
        reserveAuction.placeBid(new Bid("Rick", 26));


        System.out.println("Current high bid by: " + reserveAuction.getCurrentWinningBid().getBidder());

        ButItNowAuction butItNowAuction = new ButItNowAuction("Tech Elevator mouse", 30);

        butItNowAuction.placeBid(new Bid("Josh", 10));




    }
}
