-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)

select DISTINCT genre.genre_name
from movie
join movie_genre ON movie.movie_id = movie_genre.movie_id
join genre ON movie_genre.genre_id = genre.genre_id
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where person.person_name = 'Robert De Niro' and movie.release_date >= '2010-01-01'
order by genre.genre_name;
