package com.techelevator.reservations;

import com.techelevator.reservations.controllers.HotelController;
import com.techelevator.reservations.dao.MemoryHotelDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelReservationsApplication {

    public static void main(String[] args) {
        Hotel hotelDao = new MemoryHotelDao();
        Reservation reservationDao = new MemoryHotelDao(hotelDao);
        HotelController controller = new MemoryHotelDao(hotelDao);




        SpringApplication.run(HotelReservationsApplication.class, args);
    }

}
