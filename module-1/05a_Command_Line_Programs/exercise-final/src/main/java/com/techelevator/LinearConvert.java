package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

		final double FEET_TO_METERS = .3048;
		final double METERS_TO_FEET = 3.2808399;

		Scanner userInput = new Scanner(System.in);

		System.out.print("Please enter the length: ");
		String lengthInput = userInput.nextLine();
		int length = Integer.parseInt(lengthInput);

		System.out.print("Is the measurement in (m)eters, or (f)eet?: ");
		String unitInput = userInput.nextLine();

		if ("f".equals(unitInput)) {
			int meterLength = (int) (length * FEET_TO_METERS);
			System.out.println(length + "f is " + meterLength + "m.");
		} else if ("m".equals(unitInput)) {
			int feetLength = (int) (length * METERS_TO_FEET);
			System.out.println(length + "m is " + feetLength + "f.");
		} else {
			System.out.println(unitInput + " is an invalid choice.");
		}
	}

}
