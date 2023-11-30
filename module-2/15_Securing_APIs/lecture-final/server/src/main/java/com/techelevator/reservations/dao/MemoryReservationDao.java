package com.techelevator.reservations.dao;

import com.techelevator.reservations.exception.DaoException;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryReservationDao   {

    private static List<Reservation> reservations = new ArrayList<>();
    private HotelDao hotelDao;

    public MemoryReservationDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
        if (reservations.size() == 0) {
            init();
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Reservation> getReservationsByHotel(int hotelId) {
        List<Hotel> hotels = hotelDao.getHotels();
        boolean hotelExists = false;
        for (Hotel hotel : hotels) {
            if (hotel.getId() == hotelId) {
                hotelExists = true;
                break;
            }
        }

        if (!hotelExists) {
            return null;
        }

        List<Reservation> hotelReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getHotelId() == hotelId) {
                hotelReservations.add(r);
            }
        }

        return hotelReservations;
    }

    public Reservation getReservationById(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getId() == reservationId) {
                return res;
            }
        }
        return null;
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setId(getMaxIdPlusOne());
        this.reservations.add(reservation);
        return reservation;
    }

    public Reservation updateReservation(Reservation reservation) {
        Reservation result = reservation;
        boolean finished = false;

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId() == reservation.getId()) {
                reservations.set(i, result);
                finished = true;
                break;
            }
        }
        if (!finished) {
            throw new DaoException("Reservation to update not found");
        }

        return result;
    }

    public int deleteReservationById(int id) {
        Reservation target = null;
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                target = reservation;
            }
        }
        // when using foreach we must remove the item after iterating to avoid a ConcurrentModificationException
        if (target != null) {
            reservations.remove(target);
            return 1; // number of rows affected
        } else {
            return 0; // number of rows affected
        }
    }

    private void init() {
        LocalDate now = LocalDate.now();
        List<Hotel> hotels = hotelDao.getHotels();

        reservations.add(new Reservation(getMaxIdPlusOne(),
                hotels.get(0).getId(),
                "John Smith",
                now.toString(),
                now.plusDays(3).toString(),
                2));
        reservations.add(new Reservation(getMaxIdPlusOne(),
                hotels.get(0).getId(),
                "Sam Turner",
                now.toString(),
                now.plusDays(5).toString(),
                4));
        reservations.add(new Reservation(getMaxIdPlusOne(),
                hotels.get(1).getId(),
                "Mark Johnson",
                now.plusDays(7).toString(),
                now.plusDays(10).toString(),
                2));
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
