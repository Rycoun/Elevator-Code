package com.techelevator.guessnumber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int DEFAULT_LOWER_BOUND = 1;
    public static final int DEFAULT_UPPER_BOUND = 100;
    private static final String MESSAGE_GAME_OVER = "The same!";
    private int gameId;
    private int userId;
    private int lowerBound;
    private int upperBound;
    @JsonIgnore
    private int numberToGuess;
    private List<Integer> guesses = new ArrayList<>();

    public boolean isGameOver() {
        return getNextGuessShouldBe().equalsIgnoreCase(MESSAGE_GAME_OVER);
    }

    public String getNextGuessShouldBe() {
        // "Higher", "Lower", "The same!", "I'm not sure. No guesses have been made."

        if (guesses.isEmpty()) {
            return "I'm not sure. No guesses have been made.";
        }

        int lastGuess = guesses.get(guesses.size() - 1);

        if (lastGuess == numberToGuess) {
            return MESSAGE_GAME_OVER;
        }

        if (lastGuess > numberToGuess) {
            return "Lower";
        }

        return "Higher";
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public void setNumberToGuess(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public List<Integer> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<Integer> guesses) {
        this.guesses = guesses;
    }
}
