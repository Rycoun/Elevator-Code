package com.techelevator;

public class TriangleWall extends Wall {
    private final int base;
    private final int height;

    public TriangleWall(String name, String color, int base, int height) {
        super(name, color);
        this.base = base;
        this.height = height;


    }

    public int getArea() {
        return (base * height) / 2;

    }

    public String toString() {
        return super.name + " (" + base + "x" + height + ") " + "triangle";
    }



}
