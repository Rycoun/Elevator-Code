package com.techelevator;

public abstract class Wall {
    public String name;
    public final String color;

    public Wall(String name, String color) {
        this.name = name;
        this.color = color;

    }

    public abstract int getArea();

}
