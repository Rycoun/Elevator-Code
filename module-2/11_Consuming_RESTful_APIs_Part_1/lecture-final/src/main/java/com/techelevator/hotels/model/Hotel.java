package com.techelevator.hotels.model;

public class Hotel {

    private int id;
    private String name;
    private Address address;
    private int stars;
    private int roomsAvailable;
    private String coverImage;
    private int costPerNight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getStars() {
        return stars;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Hotel Details" +
                "\n--------------------------------------------" +
                "\n Id: " + id +
                "\n Name: " + name +
                "\n Stars: " + stars +
                "\n Rooms Available: " + roomsAvailable +
                "\n Cover Image: " + coverImage;
    }
}
