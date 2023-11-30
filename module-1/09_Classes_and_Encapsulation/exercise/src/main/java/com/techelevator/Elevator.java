package com.techelevator;

public class Elevator {
    private int currentFloor;
    private final int numberOfFloors;
    private boolean doorOpen;

    public Elevator(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.currentFloor = 1;
        this.doorOpen = false;

    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void openDoor() {
        doorOpen = true;
    }
    public void  closeDoor() {
        doorOpen = false;
    }
    public void goUp(int whatFloor) {
        if (!doorOpen && whatFloor > currentFloor && whatFloor <= numberOfFloors) {
            currentFloor = whatFloor;
        }
    }

    public void goDown(int whatFloor) {
        if (!doorOpen && whatFloor < currentFloor && whatFloor >= 1) {
            currentFloor = whatFloor;
        }
    }


}
