package com.techelevator.guessnumber.controller;

import com.techelevator.guessnumber.dao.GuessNumberDao;
import com.techelevator.guessnumber.dao.JdbcGuessNumberDao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name="Guess Random Number Game") // Used for Swagger Documentation
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


}
