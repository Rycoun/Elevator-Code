-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)
select movie.title, person.person_name
from movie
join person ON movie.director_id = person.person_id
where movie.movie_id IN (select movie_actor.movie_id from movie_actor where movie_actor.actor_id = movie.director_id)
order by movie.title;

