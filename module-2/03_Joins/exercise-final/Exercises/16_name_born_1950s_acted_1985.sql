-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985.
-- Order the results by actor from oldest to youngest.
-- (20 rows)

SELECT DISTINCT person_name, p.birthday
FROM person p
JOIN movie_actor ma ON p.person_id = ma.actor_id
JOIN movie m ON ma.movie_id = m.movie_id
WHERE p.birthday BETWEEN '1/1/1950' AND '12/31/1959'
AND m.release_date BETWEEN '1/1/1985' AND '12/31/1985'
ORDER BY p.birthday;
