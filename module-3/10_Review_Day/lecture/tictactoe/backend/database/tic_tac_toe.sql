BEGIN TRANSACTION;

DROP TABLE IF EXISTS game_result_type, game;

CREATE TABLE game_result_type
(
    game_result SERIAL PRIMARY KEY,
    game_result_text VARCHAR(10) NOT NULL
);

CREATE TABLE game
(
    game_id SERIAL PRIMARY KEY,
    game_result int NOT NULL REFERENCES game_result_type (game_result)
);

INSERT INTO game_result_type (game_result_text) VALUES ('X Win'), ('O Win'), ('Tie');

COMMIT;
