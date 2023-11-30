-- 13. The directors of the movies in the "Harry Potter Collection", sorted alphabetically.
-- (4 rows)
select DISTINCT person.person_name
from movie
join collection ON movie.collection_id = collection.collection_id
join person ON movie.director_id = person.person_id
where collection.collection_name = 'Harry Potter Collection'
order by person.person_name;

