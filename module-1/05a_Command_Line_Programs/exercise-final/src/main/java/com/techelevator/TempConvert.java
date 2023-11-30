package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);

		System.out.print("Please enter the temperature: ");
		String tempInput = userInput.nextLine();
		int temp = Integer.parseInt(tempInput);

		System.out.print("Is the temperature in (C)elsius, or (F)ahrenheit?: ");
		String scaleInput = userInput.nextLine();

		if ("F".equals(scaleInput)) {
			int celsiusTemp = (int) ((temp - 32) / 1.8);
			System.out.println(temp + "F is " + celsiusTemp + "C.");
		} else if ("C".equals(scaleInput)) {
			int fahrenheitTemp = (int) ((temp * 1.8) + 32);
			System.out.println(temp + "C is " + fahrenheitTemp + "F.");
		} else {
			System.out.println(scaleInput + " is an invalid choice.");
		}
	}

}
