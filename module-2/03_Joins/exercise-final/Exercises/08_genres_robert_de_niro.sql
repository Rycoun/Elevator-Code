-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)

SELECT DISTINCT genre_name
FROM genre g
JOIN movie_genre mg ON g.genre_id = mg.genre_id
JOIN movie_actor ma ON mg.movie_id = ma.movie_id
JOIN person p ON ma.actor_id = p.person_id
JOIN movie m on ma.movie_id = m.movie_id
WHERE person_name = 'Robert De Niro'
AND release_date >= '01/01/2010'
ORDER BY genre_name;
