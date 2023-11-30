-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'.
-- Order the results from the highest movie count to the lowest.
-- (19 rows, the highest expected count is around 400).

select genre.genre_name, count(movie.movie_id) AS num_of_movies
from movie
join movie_genre  ON movie.movie_id = movie_genre.movie_id
join genre ON movie_genre.genre_id = genre.genre_id
group by genre.genre_name
order by num_of_movies DESC;
