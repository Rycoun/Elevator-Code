package com.techelevator.farm;

public class Chicken extends FarmAnimal {

    public Chicken() {
        super("Chicken", "Bawkkkkk");
    }

    public Egg layEgg() {
        return new Egg();
    }
}
