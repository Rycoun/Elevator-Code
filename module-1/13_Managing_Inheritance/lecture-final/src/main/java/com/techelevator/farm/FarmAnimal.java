package com.techelevator.farm;

public abstract class FarmAnimal implements Singable {
    private String name;
    private String sound;
    private boolean isSleeping = false;


    public FarmAnimal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    @Override
    public final String getSound() {
        if (isSleeping) {
            return "Zzzzz";
        }

        return sound;
    }

    public abstract String eat();
}
