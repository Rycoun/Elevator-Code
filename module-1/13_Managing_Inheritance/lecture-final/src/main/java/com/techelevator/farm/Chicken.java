package com.techelevator.farm;

public final class Chicken extends FarmAnimal {

    public Chicken() {
        super("Chicken", "Bawkkkkk");
    }

    public Egg layEgg() {
        return new Egg();
    }

    @Override
    public String eat() {
        return "Bugs & Seeds";
    }
}
