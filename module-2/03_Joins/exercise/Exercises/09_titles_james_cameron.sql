-- 9. The titles of movies directed by James Cameron, sorted alphabetically.
-- (6 rows)

select movie.title
from movie
join person ON movie.director_id = person.person_id
where person.person_name = 'James Cameron'
order by movie.title;
