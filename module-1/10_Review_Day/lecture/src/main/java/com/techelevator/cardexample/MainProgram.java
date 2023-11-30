package com.techelevator.cardexample;

public class MainProgram {

	public static void main(String[] args) {

		Card aceOfSpades = new Card("Ace", "Spades");

		Card twoOfDiamonds = new Card("Two", "Diamonds");

		Card threeOfClub = new Card("Three", "Clubs");

		Card fourOfHeart = new Card("Four", "Hearts");

		aceOfSpades.flip();

		System.out.println(aceOfSpades.cardString());

		System.out.println(twoOfDiamonds.cardString());

		Deck pokerDeck = new Deck("Red");

		pokerDeck.addCard(aceOfSpades);
		pokerDeck.addCard(twoOfDiamonds);
		pokerDeck.addCard(threeOfClub);
		pokerDeck.addCard(fourOfHeart);

		System.out.println(pokerDeck.showDeck());

		pokerDeck.shuffle();

		System.out.println(pokerDeck.showDeck());

		String[] allSuits = {"Spades", "Clubs", "Diamonds", "Hearts",};

	}

}
