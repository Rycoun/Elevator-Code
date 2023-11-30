package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter a number: ");
		String limitInput = userInput.nextLine();

		int limit = Integer.parseInt(limitInput);
		int previous = 1;
		System.out.print("0, 1");

		for (int next = 1; next <= limit;) {
			System.out.print(", " + next);
			int temp = previous + next;
			previous = next;
			next = temp;
		}
	}
}
