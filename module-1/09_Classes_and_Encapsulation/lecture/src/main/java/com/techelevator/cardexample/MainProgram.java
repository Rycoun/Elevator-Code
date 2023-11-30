package com.techelevator.cardexample;

public class MainProgram {

	public static void main(String[] args) {

		Card aceOfSpade = new Card("Ace", "Spades");
		Card twoDiamond = new Card("Two", "Diamonds");

		aceOfSpade.flip();
		System.out.println(aceOfSpade.cardString());

		System.out.println(twoDiamond.cardString());


	}

}
