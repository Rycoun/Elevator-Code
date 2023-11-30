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


    // GET http://localhost:8080/hotels
    // GET http://localhost:8080/hotels?state=PA
    // GET http://localhost:8080/hotels?city=Pittsburgh
    // GET http://localhost:8080/hotels?state=PA&city=Pittsburgh

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> list(@RequestParam(defaultValue = "") String state, @RequestParam(required = false) String city) {

        return hotelDao.getHotelsByStateAndCity(state, city);

//        return hotelDao.getHotels();
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

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationDao.getReservations();
    }

    @GetMapping("/reservations/{reservationId}")
    public Reservation getReservationById(@PathVariable int reservationId) {
        return reservationDao.getReservationById(reservationId);
    }

    // http://localhost:3000/reservations?hotelId=??
    // http://localhost:3000/hotels/{}/reservations

    @GetMapping("/hotels/{id}/reservations")
    public List<Reservation> getReservationsByHotel(@PathVariable("id") int hotelId) {
        return reservationDao.getReservationsByHotel(hotelId);
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation createReservation(@RequestBody Reservation reservationToCreate) {
        return reservationDao.createReservation(reservationToCreate);
    }


}
