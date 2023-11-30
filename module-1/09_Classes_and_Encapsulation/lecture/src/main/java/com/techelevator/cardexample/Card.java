package com.techelevator.cardexample;

public class Card {
    public static final char SPADES = '\u2660';
    public static final char DIAMOND= '\u2666';
    public static final char CLUB = '\u2663';
    public static final char HEART= '\u2665';
    // rank, suit, color, faceUp
    // Behaviors
    // flip()

    private String rank;
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

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

public char getSym() {
        if (suit.equalsIgnoreCase("Spades")) {
            return SPADES;
        }
        if (suit.equalsIgnoreCase("Club")) {
}
    public String setSuit(String suit) {
        this.suit = suit;
        return suit;
    }

    public String getColor(){
        if (suit.equals("Clubs") || suit.equalsIgnoreCase("Spades")) {
            return "Black";
        }
        return "Red";

    }
    public boolean isFaceUp() {
        return faceUp;
    }
    public void flip() {
        faceUp = !faceUp;

    }

    public String cardString() {
        if (!faceUp) {
            return "##";
        }

        return rank + " of " + suit + "(" + getColor() + ")";

    }





}
