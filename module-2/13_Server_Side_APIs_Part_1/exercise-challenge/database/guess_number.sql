BEGIN TRANSACTION;

-- TODO: create tables for storing games, and guesses made for a game

-- For games, we will need to know the player's name, 
-- the lower and upper bounds of possible numbers, 
-- as well as the actual number the player should be attempting to guess.

-- For guesses, we will need to know which game the guess was for
-- and when the guess was made so that we can order guesses chronologically

COMMIT;
