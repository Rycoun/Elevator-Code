package com.techelevator.auction;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        // Create a new general auction
        System.out.println("Starting a general auction");
        System.out.println("-----------------");

        LocalDateTime endDateTime = LocalDateTime.of(2023, 10, 1, 13, 0);
        Auction generalAuction = new Auction("Tech Elevator t-shirt", endDateTime);

        Bid firstBid = new Bid("Josh", 1);
        generalAuction.placeBid(firstBid);
        generalAuction.placeBid(new Bid("Fonz", 23));
        generalAuction.placeBid(new Bid("Rick Astley", 13));
        //....
        //....
        // This might go on until the auction runs out of time or hits a max # of bids

        System.out.println("Current high bid by: " + generalAuction.getCurrentWinningBid().getBidder());


        ReserveAuction reserveAuction = new ReserveAuction("Tech Elevator backpack", 25);

        reserveAuction.placeBid(new Bid("Josh", 2));
        reserveAuction.placeBid(new Bid("Josh", 25));
        reserveAuction.placeBid(new Bid("Fonz", 35));
        reserveAuction.placeBid(new Bid("Rick", 26));


        System.out.println("Current high bid by: " + reserveAuction.getCurrentWinningBid().getBidder());


        BuyItNowAuction buyItNowAuction = new BuyItNowAuction(30, "Tech Elevator mouse");

        buyItNowAuction.placeBid(new Bid("Josh", 5));
        buyItNowAuction.placeBid(new Bid("Fonz", 45));
        buyItNowAuction.placeBid(new Bid("Josh", 29));


        System.out.println(buyItNowAuction);

        System.out.println(buyItNowAuction.getCurrentWinningBid());

        runAuction(generalAuction);
        runAuction(buyItNowAuction);
        runAuction(reserveAuction);
    }

    public static void runAuction(Auction auction) {
        Scanner scanner = new Scanner(System.in);

        while (!auction.hasAuctionEnded()) {
            System.out.println("What is your name: ");
            String userInput = scanner.nextLine();
            String bidder = userInput;

            System.out.println("What is your bid: ");
            userInput = scanner.nextLine();
            int bidAmount = Integer.parseInt(userInput);

            Bid bid = new Bid(bidder, bidAmount);

            boolean isHighBid = auction.placeBid(bid);

            if (isHighBid) {
                System.out.println("You are the current high bidder!");
            }
        }

        System.out.println("The auction has ended!");
        System.out.println("Winner: " + auction.getCurrentWinningBid().getBidder());
    }
}
