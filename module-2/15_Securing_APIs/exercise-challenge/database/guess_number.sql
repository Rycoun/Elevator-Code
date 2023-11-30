BEGIN TRANSACTION;

-- TODO: create tables for storing games, and guesses made for a game

-- For games, we will need to know the player's name, 
-- the lower and upper bounds of possible numbers, 
-- as well as the actual number the player should be attempting to guess.

-- For guesses, we will need to know which game the guess was for
-- and when the guess was made so that we can order guesses chronologically


drop table if exists users;

-- TODO: associate this table to games
create table users
(
	user_id serial primary key,
	username varchar(50) unique not null,
	password_hash varchar(200) not null,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(50),
	role varchar(20)
);

COMMIT;
