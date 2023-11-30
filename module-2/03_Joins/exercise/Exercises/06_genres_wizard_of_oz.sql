-- 6. The genres of "The Wizard of Oz" sorted in alphabetical order (A-Z).
-- (3 rows)

select genre.genre_name
from movie
join movie_genre ON movie.movie_id = movie_genre.movie_id
join genre ON movie_genre.genre_id = genre.genre_id
where movie.title = 'The Wizard of Oz'
order by genre.genre_name;