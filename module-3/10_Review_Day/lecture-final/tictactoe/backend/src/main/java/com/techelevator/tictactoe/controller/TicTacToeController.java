package com.techelevator.tictactoe.controller;

import com.techelevator.tictactoe.dao.TicTacToeDao;
import com.techelevator.tictactoe.model.Game;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Tag(name="Tic Tac Toe Game") // Used for Swagger Documentation
@RequestMapping("/games")
@CrossOrigin
public class TicTacToeController {

    private final TicTacToeDao dao;

    public TicTacToeController(TicTacToeDao dao) {
        this.dao = dao;
    }


    @GetMapping("")
    public List<Game> getAll() {
        return dao.getResults();
    }

    @PostMapping("")
    public Game saveGame(@Valid @RequestBody Game game) {
        return dao.saveGame(game);
    }
}
