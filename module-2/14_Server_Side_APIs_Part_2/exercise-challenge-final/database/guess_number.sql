BEGIN TRANSACTION;

DROP TABLE IF EXISTS guess, game;

CREATE TABLE game
(
    game_id SERIAL,
    player_name VARCHAR(100) NOT NULL,
    number_to_guess INTEGER NOT NULL,
    lower_bound INTEGER NOT NULL,
    upper_bound INTEGER NOT NULL,

    CONSTRAINT pk_game PRIMARY KEY (game_id),
    CONSTRAINT chk_upper_bound_gt_lower_bound CHECK (upper_bound > lower_bound),
    CONSTRAINT chk_number_to_guess_is_in_bounds CHECK (number_to_guess BETWEEN lower_bound AND upper_bound)
);

CREATE TABLE guess
(
    guess_id SERIAL,
    game_id INTEGER NOT NULL,
    guess INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),

    CONSTRAINT pk_guess_id PRIMARY KEY (guess_id),
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game (game_id)
);

COMMIT;
