package com.techelevator;

public class Airplane {

    private String planeNumber;
    private int totalFirstClassSeats;
    private int bookedFirstClassSeats;
    private int totalCoachSeats;
    private int bookedCoachSeats;

    public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFirstClassSeats;
        this.totalCoachSeats = totalCoachSeats;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getAvailableFirstClassSeats() {
        return totalFirstClassSeats - bookedFirstClassSeats;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }

    public int getAvailableCoachSeats() {
        return totalCoachSeats - bookedCoachSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public boolean reserveSeats(boolean firstClass, int totalNumberOfSeats) {
        if (firstClass) {
            if (totalNumberOfSeats > getAvailableFirstClassSeats()) {
                return false;
            }
            bookedFirstClassSeats += totalNumberOfSeats;
        } else {
            if (totalNumberOfSeats > getAvailableCoachSeats()) {
                return false;
            }
            bookedCoachSeats += totalNumberOfSeats;
        }
        return true;
    }

}
