package com.techelevator.cardexample;

public class Card {
    public static final char SPADES_SYMBOL = '\u2660';
    public static final char CLUBS_SYMBOL = '\u2663';
    public static final char DIAMONDS_SYMBOL = '\u2666';
    public static final char HEARTS_SYMBOL = '\u2665';

    // data
    // rank - String
    // suit - String
    // color - String
    // faceUp - boolean
    private final String rank;
    private String suit;
    private boolean faceUp = true;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(String rank, String suit, boolean faceUp) {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = faceUp;
    }

    public void doStuff() {
        System.out.println(this.rank);
    }

    // behaviors
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getColor() {
        if (suit.equalsIgnoreCase("Clubs") || suit.equalsIgnoreCase("Spades")) {
            return "Black";
        }

        return "Red";
    }

    public char getSymbol() {
        if (suit.equalsIgnoreCase("Spades")) {
            return SPADES_SYMBOL;
        }

        if (suit.equalsIgnoreCase("Clubs")) {
            return CLUBS_SYMBOL;
        }

        if (suit.equalsIgnoreCase("Diamonds")) {
            return DIAMONDS_SYMBOL;
        }

        return HEARTS_SYMBOL;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    // flip()
    public void flip() {
        faceUp = !faceUp;
    }

    public String cardString() {
        if (!faceUp) {
            return "##";
        }

        // Ace of Spades (Black)
        return rank + " of " + getSymbol() + " " + suit + "(" + getColor() + ")";
    }

    public String cardString(boolean showSymbol) {
        if (showSymbol) {
            return cardString();
        }

        if (!faceUp) {
            return "##";
        }

        return rank + " of " + suit + "(" + getColor() + ")";
    }

}
