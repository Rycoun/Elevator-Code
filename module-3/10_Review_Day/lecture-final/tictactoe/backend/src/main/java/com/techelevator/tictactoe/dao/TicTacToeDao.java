package com.techelevator.tictactoe.dao;

import com.techelevator.tictactoe.model.Game;

import java.util.List;

public interface TicTacToeDao {
    List<Game> getResults();

    Game getGameById(int gameId);

    Game saveGame(Game game);
}
