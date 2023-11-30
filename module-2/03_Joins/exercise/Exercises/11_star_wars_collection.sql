-- 11. The titles of the movies in the "Star Wars Collection" ordered by release date, most recent first. 
-- (9 rows)

select movie.title
from movie
join collection ON movie.collection_id = collection.collection_id
where collection.collection_name = 'Star Wars Collection'
order by movie.release_date DESC;

