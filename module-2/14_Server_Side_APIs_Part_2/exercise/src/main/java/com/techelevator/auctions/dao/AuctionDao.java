package com.techelevator.auctions.dao;

import com.techelevator.auctions.model.Auction;

import java.util.List;

public interface AuctionDao {

    List<Auction> getAuctions();

    Auction getAuctionById(int id);

    Auction createAuction(Auction auction);

    List<Auction> getAuctionsByTitle(String searchTern);

    List<Auction> getAuctionsByMaxBid(double maxBid);

    Auction updateAuction(Auction auction);

    int deleteAuctionById(int id);
}
