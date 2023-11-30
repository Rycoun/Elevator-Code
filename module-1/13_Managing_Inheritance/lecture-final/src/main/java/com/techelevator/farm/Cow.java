package com.techelevator.farm;

public class Cow extends FarmAnimal {

    public Cow() {
        super("Cow", "Moo");
    }

    @Override
    public String eat() {
        return "Grass";
    }
}
