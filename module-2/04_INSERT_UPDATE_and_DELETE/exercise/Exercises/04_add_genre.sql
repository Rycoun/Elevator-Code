-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)
insert into genre (genre_name)
values ('Sports');

insert into movie_genre (movie_id, genre_id)
values ((select movie_id from movie where title = 'Coach Carter'),
       (select genre_id from genre where genre_name = 'Sports'));
