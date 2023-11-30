package com.techelevator;
import java.util.*;

public class TempConvert {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the temperature right now ");
		double temp = input.nextDouble();

		System.out.print("Is the temperature you provided (in C or F) Celsius or Fahrenheit? ");
		input.nextLine();
		String celOrFah = input.nextLine();

		double convtTemp;
		String newTemp;

		if (celOrFah.equalsIgnoreCase("C")) {
			convtTemp = (temp * 1.8) + 32;
			 newTemp = "Fahrenheit";
		} else if (celOrFah.equalsIgnoreCase("F")) {
			convtTemp = (temp - 32) / 1.8;
			newTemp = "Celsius";
		} else {
			System.out.print("Not a valid input weirdo lol");
			return;

		}
		System.out.print(temp + " is " + convtTemp + " In " + newTemp);



	}

}
