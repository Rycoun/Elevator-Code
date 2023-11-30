package com.techelevator.guessnumber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.ArrayList;
import java.util.List;

// TODO: fill in this class with fields matching your database
// as well as a few derived properties.
//
// We need the player name, 
// the guesses (in chronological order), 
// the number to guess, 
// the upper and lower bounds, 
// whether the game is over
// and a hint for the user's next guess e.g. "higher", "lower", "the same", or "don't know until your first guess is made"
public class Game {
    private int gameId;
    private int userId;
    private int lowerBound;
    private int upperBound;
    @JsonIgnore
    private int numberToGuess;
    private List<Integer> guesses = new ArrayList<>();



    public boolean isGameOver() {
        if (guesses.isEmpty()) {
            return false;
        }
        int lastGuess = guesses.get(guesses.size()-1);
        return lastGuess == numberToGuess;
    }

    public String getNextGuessShouldBe() {
        if (guesses.isEmpty()) {
            return "Not sure no guess made";

        }
        int lastGuess = guesses.get(guesses.size() -1);
        if (lastGuess == numberToGuess) {
            return "the Same!!!";

        }

        if (lastGuess > numberToGuess) {
            return "go Lower";

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
