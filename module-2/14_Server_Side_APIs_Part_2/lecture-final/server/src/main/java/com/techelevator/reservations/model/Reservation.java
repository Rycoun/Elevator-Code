package com.techelevator.reservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import javax.validation.constraints.*;

import java.time.LocalDate;

public class Reservation {

//    @Positive
    private int id;

    @Positive
    private int hotelId;

    @NotBlank
    private String fullName;

    @FutureOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkinDate;

    @Future
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkoutDate;

    @Min(value = 1, message = "Must have at least 1 guest")
    private int guests;

    @AssertTrue(message = "Check-out must come after Check-in")
    private boolean isCheckoutAfterCheckin() {
        return checkoutDate.isAfter(checkinDate);
    }

    public Reservation(int id, int hotelId, String fullName, LocalDate checkinDate, LocalDate checkoutDate, int guests) {
        this.id = id;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.guests = guests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" + "\n Reservation Details"
                + "\n--------------------------------------------" + "\n Id: " + id + "\n Hotel Id: " + hotelId
                + "\n Full Name: " + fullName + "\n Checkin Date: " + checkinDate + "\n Checkout Date: " + checkoutDate
                + "\n Guests: " + guests;
    }
}
