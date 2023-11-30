package com.techelevator;

import javax.naming.Name;

public class RectangleWall extends Wall{
    private final int height;
    private final int length;


    public RectangleWall(String name, String color, int height, int length) {
        super(name, color);
        this.height = height;
        this.length = length;
    }
    public int getArea() {
        return length * height;
    }
    public String toString() {
        return super.name + " (" + height + "x" + length + ") " + "rectangle";

    }
}
