package com.techelevator;

import java.util.Scanner;
import java.io.*;

public class FizzWriter {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		FizzWriter fizzWriter = new FizzWriter();
		fizzWriter.run();
	}
	public void run() {

		System.out.println("What is the destination file? ");
		String filePath = userInput.nextLine();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 1; i <= 300; i++) {
				String output = getFizzBuzz(i);
				writer.write(output);
				writer.newLine();
			}
			System.out.println("FizzBuzz results were written to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("An error occurred while writing file.");
		}
	}
	private String getFizzBuzz(int number) {
		if (number % 3 == 0 && number % 5 == 0) {
			return "FizzBuzz";
		} else if (number % 3 == 0) {
			return "Fizz";
		} else if (number % 5 == 0) {
			return "Buzz";
		} else {
			return String.valueOf(number);
		}
	}
}