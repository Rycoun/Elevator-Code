package com.techelevator.farm;

import java.util.ArrayList;
import java.util.*;

public class Farm {

    private List<FarmAnimal> famAnimals = new ArrayList<>();

    private Tractor myTractor = new Tractor("Vroom");
    private Chicken chickenLaysEgg = new Chicken();
    private Map<String, Sellable> sellableItems = new HashMap<>();
    public Farm() {
        sellableItems.put("Tractor", myTractor);
        sellableItems.put("Egg", chickenLaysEgg.LayEgg());
    }
    public int howMuchFor() {
        Scanner scanner = new Scanner (System.in);

        System.out.println("What would you like to buy? ");
        String userInput = scanner.nextLine();

        userInput = userInput.toLowerCase();

        if (userInput.equalsIgnoreCase("Tractor")) {
            System.out.println("The price of my Tractor is " + myTractor.getPrice());
        } else if (userInput.equalsIgnoreCase("Egg")) {
            System.out.println("The price of my Chicken egg is " + chickenLaysEgg.LayEgg().getPrice());
        } else {
            System.out.println("I'm not sure what you want ");

        }
        return 0;
    }


}
