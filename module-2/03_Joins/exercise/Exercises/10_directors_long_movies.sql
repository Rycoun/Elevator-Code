-- 10. The names of directors who have directed a movie over 3 hours [180 minutes], sorted alphabetically.
-- (15 rows)
select DISTINCT person.person_name
from movie
join person ON movie.director_id = person.person_id
where movie.length_minutes > 180
order by person.person_name;

