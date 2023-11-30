-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre. 
-- Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)

select movie.title, movie.length_minutes, movie.release_date
from movie
join movie_genre ON movie.movie_id = movie_genre.movie_id
join genre ON movie_genre.genre_id = genre.genre_id
where genre.genre_name = 'Action'
order by movie.length_minutes DESC, movie.release_date DESC
LIMIT 5;
