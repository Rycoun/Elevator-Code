-- 7. The genres of movies that Christopher Lloyd has appeared in, sorted alphabetically.
-- (8 rows) Hint: DISTINCT will prevent duplicate values in your query results.

SELECT DISTINCT genre_name
FROM genre g
JOIN movie_genre mg ON g.genre_id = mg.genre_id
JOIN movie_actor ma ON mg.movie_id = ma.movie_id
JOIN person p ON ma.actor_id = p.person_id
WHERE person_name = 'Christopher Lloyd'
ORDER BY genre_name;
