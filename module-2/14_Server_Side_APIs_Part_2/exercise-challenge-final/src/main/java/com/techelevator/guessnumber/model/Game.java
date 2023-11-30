package com.techelevator.guessnumber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int DEFAULT_LOWER_BOUND = 1;
    public static final int DEFAULT_UPPER_BOUND = 100;
    private int id;
    private String playerName;
    @JsonIgnore // don't send back to client
    private int numberToGuess;
    private int lowerBound;
    private int upperBound;
    private final List<Integer> guesses = new ArrayList<>();

    public Game() {}


    public Game(String name, int numberToGuess, int lowerBound, int upperBound) {
        this.playerName = name;
        this.numberToGuess = numberToGuess;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public void setNumberToGuess(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public List<Integer> getGuesses() {
        return new ArrayList<>(guesses);
    }

    public void addGuess(int guess) {
        if (!isGameOver()) {
            guesses.add(guess);
        }
    }

    public String getNextGuessShouldBe() {
        if (guesses.isEmpty()) {
            return "I'm not sure. No guesses have been made.";
        }

        if (isGameOver()) {
            return "The same!";
        }

        int lastGuess = guesses.get(guesses.size() - 1);

        if (lastGuess < numberToGuess) {
            return "Higher";
        }

        return "Lower";
    }

    public boolean isGameOver() {
        return guesses.contains(numberToGuess);
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
