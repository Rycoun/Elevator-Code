insert into members (member_number, last_name, first_name, email, phone_number, date_of_birth, email_reminder)
update (1, 'Counasse', 'Ryan', 'rycounasse@example.com', '123-456-7890', '1980-01-15', true),
		(2, 'Fire', 'John', 'jofire@example.com', null, '1890-03-20', true),
		(3, 'Crap', 'Bruce', 'buwdiid@gmail.com', '814-867-5309','1890-09-20', false),
		(4, 'Frap', 'Jruce', 'budawawwwwwdiid@gmail.com', '814-827-5329','1890-04-20', true),
		(5, 'Counasse', 'Sruce', 'buwdiiddwda@gmail.com', '814-897-5109','1870-03-20', false),
		(6, 'Counasse', 'Lruce', 'buddadwdiid@gmail.com', '814-807-5389','1810-03-20', false),
		(7, 'Srap', 'Rruce', 'yhrn@gmail.com', '717-807-5309','1890-03-14', true),
		(8, 'Prap', 'Fruce', 'ththrg@gmail.com', '412-335-5309','1890-09-20', true);
		
insert into InterestGroups (group_number, name)
values 
	(1, 'Jogging'),
	(2, 'Sleeping'),
	(3, 'Yelling');
	
insert into events (event_number, name, description, start_date_time, duration_in_minutes, group_number)
values (1, 'we love to jog yo', 'jog it upppp', '2013-03-12', 600, 1),
 (2, 'zzzzzzzzzz', 'zzzzzzz', '2013-11-20', 253, 2),
 (3, 'we love to jog yo2', 'jog it upppp moreeee', '2013-11-22', 600, 1),
 (4, 'YELLING AT YOU', 'LEARN TO YELL', '2013-11-05', 1000, 2);
		