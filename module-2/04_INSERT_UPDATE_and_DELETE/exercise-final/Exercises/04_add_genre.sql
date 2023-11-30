-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)

INSERT INTO genre (genre_name)
VALUES ('Sports');
INSERT INTO movie_genre (genre_id, movie_id)
VALUES ((SELECT genre_id FROM genre WHERE genre_name = 'Sports'), (SELECT movie_id FROM movie WHERE title = 'Coach Carter'));
