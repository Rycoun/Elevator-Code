package com.techelevator.cardexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    public static final int FULL_DECK = 52;
    // data
    // 52 cards
    // box color
    private List<Card> cards = new ArrayList<>();
    private final String boxColor;

    public Deck(String boxColor) {
        this.boxColor = boxColor;

    }
    public Deck (String boxColor, List<Card> cardsProvided) {
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
        return "Surprise";

    }

    // behaviors
    // shuffle
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int indexSwap = random.nextInt(cards.size());
            Card first = cards.get(i);
            Card randomCard = cards.get(indexSwap);
            cards.set(i, randomCard);
            cards.set(indexSwap, first);

        }

       // Collections.shuffle(cards);
    }
    // draw one
    public Card drawOne() {
        if (cards.size() == 0) {
            return null;
        }
        Card topCard = cards.remove(cards.size() - 1);
        return topCard;

    }

    // throw
    // add card into deck

    /**
     * Adds a card to the top of the deck.
     * @param cardToAdd the card to add to the deck.
     * @return returns true when the card was added, false if not
     */

    public boolean addCard(Card cardToAdd) {

        if (cards.size() < FULL_DECK) {
            cards.add(cardToAdd);
            return true;
        }
        return false;
    }
    public String showDeck() {
        StringBuilder build = new StringBuilder();
        for (Card curCard : cards) {
           build.append(curCard.cardString()).append("\n");

        }

        return build.toString();
    }

}
