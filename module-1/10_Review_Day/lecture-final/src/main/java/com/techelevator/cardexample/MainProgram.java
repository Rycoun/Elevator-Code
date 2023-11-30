package com.techelevator.cardexample;

public class MainProgram {
	public static void main(String[] args) {

		Card aceOfSpades = new Card("Ace", "Spades");

		Card twoOfDiamonds = new Card("Two", "Diamonds");

		Card threeOfClubs = new Card("Three", "Clubs");

		Card fourOfHearts = new Card("Four", "Hearts");

		aceOfSpades.flip();

		System.out.println(aceOfSpades.cardString());

		System.out.println(twoOfDiamonds.cardString());


		Deck pokerDeck = new Deck("Red");

		pokerDeck.addCard(aceOfSpades);
		pokerDeck.addCard(twoOfDiamonds);
		pokerDeck.addCard(threeOfClubs);
		pokerDeck.addCard(fourOfHearts);

		System.out.println(pokerDeck.showDeck());

		pokerDeck.shuffle();

		System.out.println(pokerDeck.showDeck());


		Card topCard = pokerDeck.drawTop();

		System.out.println(topCard.cardString());


		String[] allSuits = { "Spades", "Clubs", "Diamonds", "Hearts" };
		String[] allRanks = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };

		Deck myNewDeck = new Deck("Blue");

		for (String suit : allSuits) {
			for (String rank : allRanks) {
				Card currCard = new Card(rank, suit);
				myNewDeck.addCard(currCard);
			}
		}

		myNewDeck.shuffle();

		System.out.println(myNewDeck.showDeck());

	}

}
