package com.techelevator;

import java.time.LocalDateTime;

public class ReserveAuction extends Auction {
    private final int reservePrice;

    public ReserveAuction(String auctionItem, int reservePrice) {
        super(auctionItem);

        this.reservePrice = reservePrice;
    }

    public ReserveAuction(String auctionItem, LocalDateTime endDateTime, int reservePrice) {
        super(auctionItem, endDateTime);

        this.reservePrice = reservePrice;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public boolean placeBid(Bid attemptedBid) {
        // if the reserve price is not met, return false
        if (attemptedBid.getAmount() < reservePrice) {
            return false;
        }

        return super.placeBid(attemptedBid);
    }
}
