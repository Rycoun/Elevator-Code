package com.techelevator.auctions.services;

import org.apiguardian.api.API;
import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions";
    private RestTemplate restTemplate = new RestTemplate();


    public Auction[] getAllAuctions() {

        return restTemplate.getForObject(API_BASE_URL, Auction[].class);
    }

    public Auction getAuction(int id) {
        String sql = API_BASE_URL + "/" + id;
        return restTemplate.getForObject(sql, Auction.class);

    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        String sql = API_BASE_URL + "?title=" + title;

        return restTemplate.getForObject(sql, Auction[].class);
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
       String sql = API_BASE_URL + "?currentBid_lte=" + price;
       return restTemplate.getForObject(sql, Auction[].class);

    }

}
