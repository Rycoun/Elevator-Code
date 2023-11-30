package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcGuessNumberDao implements GuessNumberDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGuessNumberDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Game startNewGame(String playerName) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public Game getGameById(int gameId) {
       return null;
    }

    @Override
    public Game makeGuess(int gameId, int guess) {
        return null;
    }
}
