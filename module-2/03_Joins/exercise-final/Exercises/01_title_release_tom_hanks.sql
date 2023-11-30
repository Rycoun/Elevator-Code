-- 1. The titles and release dates of movies that Tom Hanks has appeared in. 
-- Order the results by release date, newest to oldest.
-- (47 rows)

SELECT title, release_date
FROM movie m 
JOIN movie_actor ma ON m.movie_id = ma.movie_id
JOIN person p ON ma.actor_id = p.person_id
WHERE person_name = 'Tom Hanks'
ORDER BY release_date DESC;
