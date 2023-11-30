-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985.
-- Order the results by actor from oldest to youngest.
-- (20 rows)

select person.person_name, person.birthday
from movie
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where person.birthday BETWEEN '1950-01-01' AND '1959-12-31' AND YEAR(movie.release_date) = 1985
order by person.birthday;
