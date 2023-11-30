package com.techelevator.reservations.dao;

import java.util.List;
import com.techelevator.reservations.model.Reservation;

public interface ReservationDao {

    List<Reservation> getReservations();

    List<Reservation> getReservationsByHotel(int hotelId);

    Reservation getReservationById(int reservationId);

    Reservation createReservation(Reservation reservation);

}
