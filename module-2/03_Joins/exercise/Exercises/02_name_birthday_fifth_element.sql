-- 2. The names and birthdays of actors in "The Fifth Element"
-- Order the results alphabetically (A-Z) by name.
-- (15 rows)
select person.person_name, person.birthday
from movie
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where movie.title = 'The Fifth Element'
ORDER BY person.person_name;
