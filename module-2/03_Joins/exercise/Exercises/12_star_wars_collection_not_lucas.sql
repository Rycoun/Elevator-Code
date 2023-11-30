-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas, sorted alphabetically.
-- (5 rows)

select movie.title
from movie
join collection ON movie.collection_id = collection.collection_id
where collection.collection_name = 'Star Wars Collection'
and movie.director_id != (SELECT person.person_id FROM person WHERE person.person_name = 'George Lucas')
order by movie.title;
