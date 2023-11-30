BEGIN TRANSACTION;

-- TODO: create tables for storing games, and guesses made for a game

-- For games, we will need to know the player's name, 
-- the lower and upper bounds of possible numbers, 
-- as well as the actual number the player should be attempting to guess.

-- For guesses, we will need to know which game the guess was for
-- and when the guess was made so that we can order guesses chronologically

drop table if exists users, games, guesses;

create table users (
    user_id serial,
    username varchar(25) not null,
    password_hash varchar(100) not null,
    first_name varchar(25),
    last_name varchar(25),
    email varchar(50),

    constraint pk_users primary key (user_id),
    constraint uq_username unique (username)
);

create table games (
    game_id serial,
    user_id int not null,
    lower_bound int not null,
    upper_bound int not null,
    number_to_guess int not null,

    constraint pk_games primary key (game_id),
    constraint fk_users foreign key (user_id) references users (user_id),
    constraint chk_upper_bound_gt_lower_bound check (upper_bound > lower_bound),
    constraint chk_number_to_guess_between_bounds check (number_to_guess between lower_bound and upper_bound)
);

create table guesses (
    guess_id serial,
    game_id int not null,
    guess int not null,
    created_at timestamp default(current_timestamp),

    constraint pk_guesses primary key (guess_id),
    constraint fk_games foreign key (game_id) references games (game_id)
);


COMMIT;
