package com.techelevator.guessnumber.controller;

import com.techelevator.guessnumber.dao.GuessNumberDao;
import com.techelevator.guessnumber.model.Game;
import com.techelevator.guessnumber.model.Guess;
import com.techelevator.guessnumber.security.dao.UserDao;
import com.techelevator.guessnumber.security.model.User;
import com.techelevator.guessnumber.services.RandomNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class GuessNumberController {

    private final GuessNumberDao guessNumberDao;
    private final RandomNumberService randomNumberService;
    private final UserDao userDao;

    public GuessNumberController(GuessNumberDao guessNumberDao, RandomNumberService randomNumberService, UserDao userDao) {
        this.guessNumberDao = guessNumberDao;
        this.randomNumberService = randomNumberService;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return guessNumberDao.getAllGames();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/games")
    public Game startNewGame(Principal principal) {
        String username = principal.getName();
        User loggedInUser = userDao.getUserByUsername(username);

        int random = randomNumberService.getRandom(Game.DEFAULT_LOWER_BOUND, Game.DEFAULT_UPPER_BOUND);

        Game gameToCreate = new Game();
        gameToCreate.setUserId(loggedInUser.getId());
        gameToCreate.setLowerBound(Game.DEFAULT_LOWER_BOUND);
        gameToCreate.setUpperBound(Game.DEFAULT_UPPER_BOUND);
        gameToCreate.setNumberToGuess(random);

        return guessNumberDao.startNewGame(gameToCreate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/games/{gameId}/guess")
    public Game makeAGuess(@RequestBody @Valid Guess guess, @PathVariable int gameId, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userDao.getUserByUsername(username);

        Game gameToGuessOn = guessNumberDao.getGameById(gameId);

        if (gameToGuessOn == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game does not exist.");
        }

        if (loggedInUser.getId() != gameToGuessOn.getUserId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot guess on another player's game.");
        }

        if (guess.getGuess() > gameToGuessOn.getUpperBound() || guess.getGuess() < gameToGuessOn.getLowerBound()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your guess is out of bounds.");
        }

        if (gameToGuessOn.isGameOver()) {
            return gameToGuessOn;
        }

        return guessNumberDao.makeAGuess(guess, gameId);
    }

}
