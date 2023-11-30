package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }

    @GetMapping
    public List<Auction> list() {
        return auctionDao.getAuctions();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return auctionDao.getAuctionById(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) {
        return auctionDao.createAuction(auction);
    }

  /*  @RequestMapping(method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String titleLike, @RequestParam(defaultValue = "0.0") double currentBid) {
        if (!titleLike.isEmpty() && currentBid > 0) {
            return auctionDao.getAuctionsByTitleAndMaxBid(titleLike, currentBid);
        } else if (!titleLike.isEmpty()) {
            return auctionDao.getAuctionsByTitle(titleLike);
        } else if (currentBid > 0) {
            return auctionDao.getAuctionsByMaxBid(currentBid);

        } else {
            return auctionDao.getAuctions();
        }



    }


*/
}