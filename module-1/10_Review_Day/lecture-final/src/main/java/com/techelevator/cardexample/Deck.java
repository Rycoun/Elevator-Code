package com.techelevator.cardexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    public static final int FULL_DECK_SIZE = 52;
    // data
    //  - 52 cards
    //  - box color
    private List<Card> cards = new ArrayList<>();
    private final String boxColor;

    public Deck(String boxColor) {
        this.boxColor = boxColor;
    }

    public Deck(String boxColor, List<Card> cardsProvided) {
        this.boxColor = boxColor;
        this.cards = new ArrayList<>(cardsProvided);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void setCards(List<Card> newCards) {
        this.cards = new ArrayList<>(newCards);
    }

    public String getBoxColor() {
        return "Surprise!";
    }

    // behaviors
    //  - shuffle

    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < cards.size(); i++) {
            // generate a random index
            int indexToSwapWith = random.nextInt(cards.size());

            Card firstCard = cards.get(i);
            Card randomCard = cards.get(indexToSwapWith);

            cards.set(i, randomCard);
            cards.set(indexToSwapWith, firstCard);
        }

        //Collections.shuffle(cards);
    }

    //  - draw one

    public Card drawTop() {
        if (cards.size() == 0) {
            return null;
        }

        Card theTopCard = cards.remove(cards.size() - 1);

        return theTopCard;
    }

    //  - throw
    //  - add card

    /**
     * Adds a card to the top of the deck.
     *
     * @param cardToAdd the card to add to the deck
     * @return true when the card added successfully; false otherwise
     */
    public boolean addCard(Card cardToAdd) {
        if (cards.size() < FULL_DECK_SIZE) {
            cards.add(cardToAdd);
            return true;
        }

        return false;
    }

    public String showDeck() {
        StringBuilder builder = new StringBuilder();

        for (Card currCard : cards) {
            builder.append(currCard.cardString()).append("\n");
        }

        return builder.toString();
    }

}
