package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.exception.DaoException;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class    HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController(HotelDao hotelDao, ReservationDao reservationDao) {
        this.hotelDao = hotelDao;
        this.reservationDao = reservationDao;
    }

    /**
     * /hotels
     * /hotels?state=ohio
     * /hotels?state=ohio&city=cleveland
     *
     * @param state the state to filter by
     * @param city  the city to filter by
     * @return a list of hotels that match the city & state
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> list(@RequestParam(required=false) String state, @RequestParam(required = false) String city) {
        return hotelDao.getHotelsByStateAndCity(state, city);
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {
        Hotel hotel = hotelDao.getHotelById(id);
        if (hotel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found.");
        } else {
            return hotel;
        }
    }

    /**
     * Returns all reservations in the system
     *
     * @return all reservations
     */
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservations() {
        return reservationDao.getReservations();
    }

    /**
     * Get a reservation by its id
     *
     * @param id
     * @return a single reservation
     */
    @RequestMapping(path = "reservations/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable int id) {
        Reservation reservation = reservationDao.getReservationById(id);
        if (reservation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        } else {
            return reservation;
        }
    }

    /**
     * List of reservations by hotel
     *
     * @param hotelId
     * @return all reservations for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservationsByHotel(@PathVariable("id") int hotelId) {
        List<Reservation> reservations = reservationDao.getReservationsByHotel(hotelId);
        if (reservations == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found.");
        } else {
            return reservations;
        }
    }

    /**
     * Create a new reservation for a given hotel
     *
     * @param reservation
     */
    @ResponseStatus(HttpStatus.CREATED) // 201
    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation addReservation(@Valid @RequestBody Reservation reservation) {
        return reservationDao.createReservation(reservation);
    }

    @RequestMapping(path = "/reservations/{reservationId}", method = RequestMethod.PUT)
    public Reservation updateReservation(@Valid @RequestBody Reservation reservation, @PathVariable int reservationId) {

//        if (reservation.getId() != reservationId) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT);
//        }

        reservation.setId(reservationId);

        try {
            return reservationDao.updateReservation(reservation);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation was not found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @RequestMapping(path = "/reservations/{reservationId}", method = RequestMethod.DELETE)
    public void deleteReservation(@PathVariable int reservationId) {
        reservationDao.deleteReservationById(reservationId);
    }
}
