package com.techelevator;

public class Airplane {
    private String planeNumber;
    private int totalFirstClassSeats;
    private int bookedFirstClassSeats;
    private int totalCoachSeats;
    private int bookedCoachSeats;

    public Airplane (String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFirstClassSeats;
        this.totalCoachSeats = totalCoachSeats;

        this.bookedCoachSeats = 0;
        this.bookedFirstClassSeats = 0;
    }
    public int getAvailableCoachSeats() {
        return totalCoachSeats - bookedCoachSeats;
    }
    public int getAvailableFirstClassSeats() {
        return totalFirstClassSeats - bookedFirstClassSeats;
    }

    public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
        if (forFirstClass) {
            if (totalNumberOfSeats <= getAvailableFirstClassSeats()) {
                bookedFirstClassSeats += totalNumberOfSeats;
                return true;
            }
        } else {
            if (totalNumberOfSeats <= getAvailableCoachSeats()) {
                bookedCoachSeats += totalNumberOfSeats;
                return true;
            }
        }
        return false;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }
}
