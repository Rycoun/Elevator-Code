package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.MemoryHotelDao;
import com.techelevator.reservations.dao.MemoryReservationDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController() {
        this.hotelDao = new MemoryHotelDao();
        this.reservationDao = new MemoryReservationDao(hotelDao);
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */

    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> list(@RequestParam(defaultValue = "") String state, @RequestParam(required = false) String city) {
        return hotelDao.getHotelsByStateAndCity(state,city);

    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {
        return hotelDao.getHotelById(id);
    }
    @GetMapping("/hotels/{id}/")
    public List<Reservation> getReservationByHotel(@PathVariable("id") int hotelId) {
        return reservationDao.getReservationsByHotel(hotelId);

    }
    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation createReservation (@RequestBody Reservation reservationToCreate) {
        return reservationDao.createReservation(reservationToCreate);
    }
}
