package com.techelevator.farm;

import java.util.ArrayList;
import java.util.List;

public class OldMacdonald {
	public static void main(String[] args) {

		Cow cow = new Cow();

		Chicken chicken = new Chicken();

//		String name = cow.getName();
//		String sound = cow.getSound();
//
//		System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
//		System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
//		System.out.println("With a " + sound + " " + sound + " here");
//		System.out.println("And a " + sound + " " + sound + " there");
//		System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
//		System.out.println();
//
//		name = chicken.getName();
//		sound = chicken.getSound();
//
//		System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
//		System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
//		System.out.println("With a " + sound + " " + sound + " here");
//		System.out.println("And a " + sound + " " + sound + " there");
//		System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
//		System.out.println();

		List<FarmAnimal> allAnimals = new ArrayList<>();
		allAnimals.add(cow);
		allAnimals.add(chicken);

//		for (FarmAnimal animal : allAnimals) {
//			String name = animal.getName();
//			String sound = animal.getSound();
//
//			if (animal instanceof Chicken) {
//				Chicken chickeee = (Chicken) animal;
//
//			}
//
//			System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
//			System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
//			System.out.println("With a " + sound + " " + sound + " here");
//			System.out.println("And a " + sound + " " + sound + " there");
//			System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
//			System.out.println();
//		}

		doOldMacDonaldSinging(allAnimals);


		Tractor myTractor = new Tractor("Vroom Vroom!");

		System.out.println("And the sound it makes is.... " + cow.getSound());
		System.out.println("And the sound it makes is.... " + chicken.getSound());
		System.out.println("And the sound it makes is.... " + myTractor.getSound());

		Singable[] allSingables = { cow, chicken, myTractor };
		for (Singable singableThing : allSingables) {
			System.out.println("And the sound it makes is.... " + singableThing.getSound());
		}
	}

	public static void doOldMacDonaldSinging(List<FarmAnimal> allAnimals) {
		for (FarmAnimal animal : allAnimals) {
			String name = animal.getName();
			String sound = animal.getSound();

			if (animal instanceof Chicken) {
				Chicken chickeee = (Chicken) animal;

			}

			System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
			System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
			System.out.println("With a " + sound + " " + sound + " here");
			System.out.println("And a " + sound + " " + sound + " there");
			System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
			System.out.println();
		}
	}

}