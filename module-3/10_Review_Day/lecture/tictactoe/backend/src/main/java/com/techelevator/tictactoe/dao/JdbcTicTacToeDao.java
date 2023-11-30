package com.techelevator.tictactoe.dao;

import com.techelevator.tictactoe.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTicTacToeDao implements TicTacToeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTicTacToeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Game> getResults() {
        List<Game> games = new ArrayList<>();

        String sql = "SELECT * FROM game;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while(rowSet.next()) {
            Game game = new Game();
            game.setGameId(rowSet.getInt("game_id"));
            game.setGameResult(rowSet.getInt("game_result"));
            games.add(game);
        }

        return games;
    }

    @Override
    public Game getGameById(int gameId) {
        String sql = "SELECT * FROM game WHERE game_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, gameId);

        if (rowSet.next()) {
            Game game = new Game();
            game.setGameId(rowSet.getInt("game_id"));
            game.setGameResult(rowSet.getInt("game_result"));

            return game;
        }

        return null;
    }

    @Override
    public Game saveGame(Game game) {
        String sql = "INSERT INTO game (game_result) VALUES (?) RETURNING game_id;";

        Integer gameId = jdbcTemplate.queryForObject(sql, Integer.class, game.getGameResult());


        return getGameById(gameId);
    }
}
