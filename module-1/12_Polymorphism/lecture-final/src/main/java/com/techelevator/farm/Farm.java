package com.techelevator.farm;

import java.util.*;

public class Farm {

    private List<FarmAnimal> farmAnimals = new ArrayList<>();

    private Tractor myTractor = new Tractor("Vroom");
    private Chicken chickenThatLaysEggs = new Chicken();

    private Map<String, Sellable> sellableItems = new HashMap<>();

    public Farm(Map<String, Sellable> sellableItems) {
        sellableItems.put("tractor", myTractor);
        sellableItems.put("egg", chickenThatLaysEggs.layEgg());
    }

    public int howMuchForYour() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to buy?");

        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();

        if (sellableItems.containsKey(userInput)) {
            System.out.println("The price for " + userInput + " is: " + sellableItems.get(userInput).getPrice());
        } else {
            System.out.println("I'm not sure. I don't have one of those!");
        }
        return 0;
    }
}
