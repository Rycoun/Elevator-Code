-- 1. The titles and release dates of movies that Tom Hanks has appeared in. 
-- Order the results by release date, newest to oldest.
-- (47 rows)
select movie.title, movie.release_date
from movie
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where person.person_name = 'Tom Hanks'
ORDER BY movie.release_date DESC;






