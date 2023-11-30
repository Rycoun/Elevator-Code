package com.techelevator;

public class SquareWall extends RectangleWall{

    public SquareWall(String name, String color, int sideLength) {
        super(name, color, sideLength, sideLength);

    }

    public String toString() {
    return super.name + " (3x3) " + "square";

    }
}
