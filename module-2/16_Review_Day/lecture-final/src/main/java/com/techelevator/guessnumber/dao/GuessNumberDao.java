package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.Game;
import com.techelevator.guessnumber.model.Guess;

import java.util.List;

public interface GuessNumberDao {
    List<Game> getAllGames();

    Game getGameById(int gameId);

    Game startNewGame(Game gameToCreate);

    Game makeAGuess(Guess guess, int gameId);
}
