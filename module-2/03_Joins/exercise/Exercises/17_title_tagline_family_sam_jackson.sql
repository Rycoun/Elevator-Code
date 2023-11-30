-- 17. The titles and taglines of movies that are in the "Family" genre that Samuel L. Jackson has acted in.
-- Order the results alphabetically by movie title.
-- (4 rows)

select movie.title, movie.tagline
from movie
join movie_genre ON movie.movie_id = movie_genre.movie_id
join genre ON movie_genre.genre_id = genre.genre_id
join movie_actor ON movie.movie_id = movie_actor.movie_id
join person ON movie_actor.actor_id = person.person_id
where genre.genre_name = 'Family' AND person.person_name = 'Samuel L. Jackson'
order by movie.title;
