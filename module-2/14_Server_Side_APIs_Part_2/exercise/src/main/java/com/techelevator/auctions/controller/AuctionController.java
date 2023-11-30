package com.techelevator.auctions.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Constraint;
import javax.validation.Valid;
@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {

        if (!title_like.equals("")) {
            return auctionDao.getAuctionsByTitle(title_like);
        }
        if (currentBid_lte > 0) {
            return auctionDao.getAuctionsByMaxBid(currentBid_lte);
        }

        return auctionDao.getAuctions();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        Auction auction = auctionDao.getAuctionById(id);
        if (auction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction Not Found");
        } else {
            return auctionDao.getAuctionById(id);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Auction create(@Validated @RequestBody Auction auction) {
        if (auction == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return auctionDao.createAuction(auction);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Auction update(@PathVariable int id, @Valid @RequestBody Auction updatedAuction) {
        Auction currentAuction = auctionDao.getAuctionById(id);
        if (currentAuction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            updatedAuction.setId(id);
            return auctionDao.updateAuction(updatedAuction);
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        Auction theAuction = auctionDao.getAuctionById(id);
        if (theAuction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        auctionDao.deleteAuctionById(id);
    }






}
