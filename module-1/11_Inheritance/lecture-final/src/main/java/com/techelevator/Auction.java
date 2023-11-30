package com.techelevator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Auction {
    private final String auctionItem;
    private final List<Bid> allBids = new ArrayList<>();
    private Bid currentWinningBid = new Bid("", 0);
    private final LocalDateTime endDateTime;

    public Auction(String auctionItem) {
        this.auctionItem = auctionItem;
        this.endDateTime = LocalDateTime.now().plusDays(7);
    }
    public Auction(String auctionItem, LocalDateTime endDateTime) {
        this.auctionItem = auctionItem;
        this.endDateTime = endDateTime;
    }

    public String getAuctionItem() {
        return auctionItem;
    }

    public Bid getCurrentWinningBid() {
        return currentWinningBid;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public boolean hasAuctionEnded() {
        return LocalDateTime.now().isAfter(endDateTime);
    }

    public boolean placeBid(Bid attemptedBid) {
        if (hasAuctionEnded()) {
            return false;
        }

        allBids.add(attemptedBid);

        if (attemptedBid.getAmount() > currentWinningBid.getAmount()) {
            currentWinningBid = attemptedBid;
            return true;
        }

        return false;
    }

}
