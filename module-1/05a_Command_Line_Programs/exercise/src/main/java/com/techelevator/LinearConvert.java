package com.techelevator;
import java.util.*;

public class LinearConvert {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the length pleaseeeeeeee ");
		double length = keyboard.nextDouble();

		System.out.print("Is the length in meters or feet? (M or F) ");
		char mesu = keyboard.next().charAt(0);

		double meter2feet = 3.2808399;
		double feet2meter = 0.3048;

		double convertLength;
		String measure;

		if (mesu == 'm' || mesu == 'M') {
			convertLength = length * meter2feet;
			measure = "Feet";

		} else if (mesu == 'f' || mesu == 'F') {
			convertLength = length * feet2meter;
			measure = "meter";
		} else {
			System.out.print("That's not a valid input an you know it lol ");
			return;
		}
		System.out.printf(length + mesu + " is " + convertLength + " in " + measure);

	}

}
