package com.techelevator.reservations.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;

public class MemoryReservationDao implements ReservationDao {

    private List<Reservation> reservations;
    private HotelDao hotelDao;

    public MemoryReservationDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
        init();
    }

    private void init() {
        LocalDate now = LocalDate.now();
        List<Hotel> hotels = hotelDao.getHotels();
        reservations = new ArrayList<>();

        reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(0).getId(), "John Smith", now.toString(),
                now.plusDays(3).toString(), 2));
        reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(0).getId(), "Sam Turner", now.toString(),
                now.plusDays(5).toString(), 4));
        reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(1).getId(), "Mark Johnson",
                now.plusDays(7).toString(), now.plusDays(10).toString(), 2));
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByHotel(int hotelId) {

        List<Reservation> hotelReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getHotelId() == hotelId) {
                hotelReservations.add(r);
            }
        }

        return hotelReservations;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getId() == reservationId) {
                return res;
            }
        }
        return null;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        reservation.setId(getMaxIdPlusOne());
        this.reservations.add(reservation);
        return reservation;
    }

    /**
     * finds the max id in the list of reservations and returns it
     *
     * @return int the max id
     */
    private int getMaxId() {
        int maxId = 0;
        for (Reservation r : reservations) {
            if (r.getId() > maxId) {
                maxId = r.getId();
            }
        }
        return maxId;
    }

    /**
     * Adds 1 to the max id and returns it
     *
     * @return
     */
    private int getMaxIdPlusOne() {
        return getMaxId() + 1;
    }

}
