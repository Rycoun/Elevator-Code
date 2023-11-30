package com.techelevator.tictactoe.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Game {
    public static final int GAME_RESULT_X_WIN = 1;
    public static final int GAME_RESULT_O_WIN = 2;
    public static final int GAME_RESULT_TIE = 3;

    private int gameId;

    @Min(value = 1)
    @Max(value = 3)
    private int gameResult;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameResult() {
        return gameResult;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }
}
