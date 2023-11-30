package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.Game;

import java.util.List;

public interface GuessNumberDao {
    Game startNewGame(String playerName);

    List<Game> getAll();

    Game getGameById(int gameId);

    Game makeGuess(int gameId, int guess);
}
