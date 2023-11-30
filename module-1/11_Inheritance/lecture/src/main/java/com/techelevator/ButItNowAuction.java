package com.techelevator;

import java.time.LocalDateTime;

public class ButItNowAuction extends Auction {
    private final int butItNowPrice;

    public ButItNowAuction(String auctionItem, int butItNowPrice) {
        super(auctionItem);
        this.butItNowPrice = butItNowPrice;
    }

    public ButItNowAuction(String auctionItem, LocalDateTime endDateTime, int butItNowPrice) {
        super(auctionItem, endDateTime);
        this.butItNowPrice = butItNowPrice;
    }

    public int getButItNowPrice() {
        return butItNowPrice;
    }

    public boolean isAuctionOver(){
        if (getCurrentWinningBid().getAmount() >= butItNowPrice || super.isAuctionOver()) {
            return true;
        }
        return false;
    }

    public boolean placeBid(Bid attemptedBid) {
        if (attemptedBid.getAmount() > butItNowPrice) {
            Bid updateBid = new Bid(attemptedBid.getBidder(), butItNowPrice);
            super.placeBid(updateBid);
        }

        return super.placeBid(attemptedBid);

    }
    public void buyItNow(String bidder) {
        Bid newBid = new Bid(bidder, butItNowPrice);
        placeBid(newBid);
    }

    public String toString() {
        return "Item for sale: " + getAuctionItem() + " Buy it now for: $" + butItNowPrice;

    }

}
