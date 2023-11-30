package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.Game;
import com.techelevator.guessnumber.model.Guess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGuessNumberDao implements GuessNumberDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGuessNumberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        String sql = "select * from games order by game_id asc;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Game game = mapRowToGame(results);

            games.add(game);
        }

        return games;
    }

    @Override
    public Game startNewGame(Game gameToCreate) {
        String sql = "insert into games (user_id, lower_bound, upper_bound, number_to_guess) values (?,?,?,?) returning game_id;";

        int gameId = jdbcTemplate.queryForObject(sql, Integer.class,
                gameToCreate.getUserId(),
                gameToCreate.getLowerBound(),
                gameToCreate.getUpperBound(),
                gameToCreate.getNumberToGuess());

        return getGameById(gameId);
    }

    @Override
    public Game makeAGuess(Guess guess, int gameId) {

        String sql = "insert into guesses (game_id, guess) values (?,?);";

        jdbcTemplate.update(sql, gameId, guess.getGuess());

        return getGameById(gameId);
    }

    public Game getGameById(int gameId) {
        String sql = "select * from games where game_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);

        if (results.next()) {
            Game game = mapRowToGame(results);

            return game;
        }

        return null;
    }

    private List<Integer> getGuessesForGame(int gameId) {
        List<Integer> guesses = new ArrayList<>();

        String sql = "select * from guesses where game_id = ? order by created_at asc;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);

        while (results.next()) {
            int guess = results.getInt("guess");

            guesses.add(guess);
        }

        return guesses;
    }

    private Game mapRowToGame(SqlRowSet results) {
        Game game = new Game();

        game.setGameId(results.getInt("game_id"));
        game.setUserId(results.getInt("user_id"));
        game.setLowerBound(results.getInt("lower_bound"));
        game.setUpperBound(results.getInt("upper_bound"));
        game.setNumberToGuess(results.getInt("number_to_guess"));

        List<Integer> guessesForGame = getGuessesForGame(game.getGameId());
        game.setGuesses(guessesForGame);

        return game;
    }

}
