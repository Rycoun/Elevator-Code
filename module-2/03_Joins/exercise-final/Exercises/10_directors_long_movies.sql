-- 10. The names of directors who have directed a movie over 3 hours [180 minutes], sorted alphabetically.
-- (15 rows)

SELECT DISTINCT person_name
FROM movie m
JOIN person p ON m.director_id = p.person_id 
WHERE length_minutes > 180
ORDER BY person_name;
