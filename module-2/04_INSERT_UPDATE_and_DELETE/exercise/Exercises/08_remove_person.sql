-- 8. Remove "Penn Jillette" from the person table 
-- You'll have to remove data from another table before you can make him "disappear" (Get it? Because he's a magician...) (1 row each)

delete from movie_actor
where actor_id = (select person_id from person where person_name = 'Penn Jillette');

delete from person
where person_name = 'Penn Jillette';