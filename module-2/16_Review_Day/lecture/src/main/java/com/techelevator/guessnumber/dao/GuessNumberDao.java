package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.Game;

import java.util.List;

public interface GuessNumberDao {
    Game startNewGame(Game gameToCreate);

    List<Game> getAllGames();

    Game getGameById(int gameId);

    Game makeGuess(int gameId, int guess);
}
