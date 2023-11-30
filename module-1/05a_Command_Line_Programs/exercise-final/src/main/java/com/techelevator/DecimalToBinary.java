package com.techelevator;

import java.util.Scanner;

public class DecimalToBinary {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter in a series of decimal values (separated by spaces): ");
		String input = userInput.nextLine();

		// Split the string
		String[] numbersAsStrings = input.split(" ");

		for (int ix = 0; ix < numbersAsStrings.length; ix++) {
			int decimal = Integer.parseInt(numbersAsStrings[ix]);

			String binary = "";
			for (; decimal >= 0; decimal = decimal / 2) { // the first and last section of the for loop are optional.  Here, we do not do anything before the for loop begins.
				binary = (decimal % 2) + binary;
				if (decimal == 0) {
					break;
				}
			}

			System.out.println(numbersAsStrings[ix] + " in binary is " + binary);
		}
	}
}
