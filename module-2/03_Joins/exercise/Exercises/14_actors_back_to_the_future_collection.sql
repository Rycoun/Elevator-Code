-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection", sorted alphabetically.
-- (28 rows)
select DISTINCT person.person_name
from movie
join collection ON movie.collection_id = collection.collection_id
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where collection.collection_name = 'Back to the Future Collection'
order by person.person_name;

