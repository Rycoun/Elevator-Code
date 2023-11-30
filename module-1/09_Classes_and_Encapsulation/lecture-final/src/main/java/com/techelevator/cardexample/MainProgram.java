package com.techelevator.cardexample;

public class MainProgram {

	public static void main(String[] args) {

		Card aceOfSpades = new Card("Ace", "Spades");

		Card twoOfDiamonds = new Card("Two", "Diamonds");

		aceOfSpades.flip();

		System.out.println(aceOfSpades.cardString());

		System.out.println(twoOfDiamonds.cardString());


	}

}
