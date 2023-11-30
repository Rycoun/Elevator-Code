package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.RandomGenerator;
import com.techelevator.guessnumber.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGuessNumberDao implements GuessNumberDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGuessNumberDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Game startNewGame(String playerName) {

        int numberToGuess = RandomGenerator.getRandom(Game.DEFAULT_LOWER_BOUND, Game.DEFAULT_UPPER_BOUND);

        Game newGame = new Game(playerName, numberToGuess, Game.DEFAULT_LOWER_BOUND, Game.DEFAULT_UPPER_BOUND);


        String sql = "" +
                "INSERT INTO game " +
                "   (player_name, lower_bound, upper_bound, number_to_guess) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING game_id;";

        Integer gameId = jdbcTemplate.queryForObject(sql, Integer.class,
                newGame.getPlayerName(),
                newGame.getLowerBound(),
                newGame.getUpperBound(),
                newGame.getNumberToGuess());

        return getGameById(gameId);
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();

        String sql = "" +
                "SELECT * " +
                "FROM game " +
                "LEFT JOIN guess USING (game_id) " +
                "ORDER BY game.game_id ASC, guess.created_at ASC;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        if (!rowSet.next()) return games;

        while (!rowSet.isAfterLast()) {
            Game g = mapRowToGame(rowSet);
            games.add(g);
        }

        return games;
    }

    @Override
    public Game getGameById(int gameId) {
        String sql = "" +
                "SELECT * " +
                "FROM game " +
                "LEFT JOIN guess USING (game_id) " +
                "WHERE game_id = ? " +
                "ORDER BY game.game_id ASC, guess.created_at ASC;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);

        if (rowSet.next()) {
            return mapRowToGame(rowSet);
        }

        return null;
    }

    @Override
    public Game makeGuess(int gameId, int guess) {
        String sql = "" +
                "INSERT INTO guess (game_id, guess) VALUES (?, ?);";

        jdbcTemplate.update(sql, gameId, guess);

        return getGameById(gameId);
    }

    private Game mapRowToGame(SqlRowSet rowSet) {
        Game g = new Game();

        g.setId(rowSet.getInt("game_id"));
        g.setNumberToGuess(rowSet.getInt("number_to_guess"));
        g.setPlayerName(rowSet.getString("player_name"));

        while(g.getId() == rowSet.getInt("game_id")) {
            int guess = rowSet.getInt("guess");
            if (!rowSet.wasNull()) {
                g.addGuess(guess);
            }
            if (!rowSet.next()) break;
        }

        return g;
    }
}
