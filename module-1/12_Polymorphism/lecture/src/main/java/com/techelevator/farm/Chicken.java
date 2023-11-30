package com.techelevator.farm;

import javax.print.DocFlavor;

public class Chicken extends FarmAnimal {
    public Chicken() {
        super("Chicken", "Bawkkk");


    }

    public Egg LayEgg() {
        return new Egg();

    }
}
