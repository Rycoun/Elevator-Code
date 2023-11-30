package com.techelevator.guessnumber.controller;

import com.techelevator.guessnumber.dao.GuessNumberDao;
import com.techelevator.guessnumber.dao.JdbcGuessNumberDao;
import com.techelevator.guessnumber.model.Game;
import com.techelevator.guessnumber.model.Guess;
import com.techelevator.guessnumber.model.Player;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Guess Random Number Game")
@RequestMapping("/games")
public class GuessNumberController {

    private final GuessNumberDao dao;

    public GuessNumberController() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/guess_number");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        this.dao = new JdbcGuessNumberDao(dataSource);
    }

    @GetMapping
    public List<Game> getAll() {
        return dao.getAll();
    }

    @GetMapping("/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        return dao.getGameById(gameId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game startNewGame(@RequestBody Player player) {
        return dao.startNewGame(player.getPlayerName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{gameId}/guess")
    public Game makeGuess(@PathVariable int gameId, @RequestBody Guess guess) {
        return dao.makeGuess(gameId, guess.getGuess());
    }
}
