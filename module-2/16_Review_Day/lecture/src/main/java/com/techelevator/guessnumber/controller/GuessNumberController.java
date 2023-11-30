package com.techelevator.guessnumber.controller;

import com.techelevator.guessnumber.dao.GuessNumberDao;
import com.techelevator.guessnumber.dao.JdbcGuessNumberDao;
import com.techelevator.guessnumber.model.Game;
import com.techelevator.guessnumber.security.dao.UserDao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
@Tag(name="Guess Random Number Game") // Used for Swagger Documentation
@RequestMapping("/games")
public class GuessNumberController {


    private final GuessNumberDao guessNumberDao;


    public GuessNumberController() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/guess_number");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        this.guessNumberDao = new JdbcGuessNumberDao(dataSource);

    }

    @PreAuthorize("permitAll")
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return guessNumberDao.getAllGames();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/games/{gameId}/guess")
    public Game startNewGame(Principal principal) {
        String username = principal.getName();

        Game gameToCreate = new Game();
        gameToCreate.setUserId();
        gameToCreate.setLowerBound();
        gameToCreate.setUpperBound();
        gameToCreate.setNumberToGuess();

        guessNumberDao.startNewGame(gameToCreate);
    }
}