package com.techelevator.farm;

public class Tractor implements Sellable, Singable {
    private final String sound;

    public Tractor(String sound) {
        this.sound = sound;
    }

    @Override
    public String getSound() {
        return sound;
    }

    @Override
    public String getName() {
        return "John Deere";
    }

    @Override
    public int getPrice() {
        return 1_500;
    }
}
