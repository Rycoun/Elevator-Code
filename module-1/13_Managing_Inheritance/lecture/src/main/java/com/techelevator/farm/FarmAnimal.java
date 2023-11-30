package com.techelevator.farm;

public class FarmAnimal implements Singable {
    private String name;
    private String sound;

    public FarmAnimal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSound() {
        return sound;
    }

}
