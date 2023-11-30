/*
 customer
 artist
 painting
 purchase
 
 artist_painting??
 purchase_painting??
*/

drop table if exists customer, artist, painting, purchase, artist_painting;

create table customer (
	customer_id serial primary key,
	name varchar(100) not null,
	phone_number varchar(15),
	address varchar(100)
);

create table artist (
	artist_id serial,
	name varchar(100) not null,
	
	constraint pk_artist primary key (artist_id)
);

create table painting (
	painting_id serial,
	title varchar(500) not null,
	
	constraint pk_painting primary key (painting_id)
);

create table purchase (
	purchase_id serial,
	customer_id integer not null,
	painting_id integer not null,
	purchase_date date not null default(current_date),
	sales_price numeric(15, 2) not null,
	
	constraint pk_purchase primary key (purchase_id),
	constraint fk_customer foreign key (customer_id) references customer(customer_id),
	constraint fk_painting foreign key (painting_id) references painting(painting_id),
	constraint chk_sales_price_is_positive check (sales_price >= 0)
);

create table artist_painting (
	artist_id integer not null,
	painting_id integer not null,
	
	constraint pk_artist_painting primary key (artist_id, painting_id),
	constraint fk_artist foreign key (artist_id) references artist(artist_id),
	constraint fk_painting foreign key (painting_id) references painting(painting_id)
);


insert into customer (name) values ('Walt');
insert into artist (name) values ('Bryce');
insert into painting (title) values ('Tech Elevator Campus');

insert into artist_painting (artist_id, painting_id)
values ( (select artist_id from artist where name = 'Bryce'), (select painting_id from painting where title = 'Tech Elevator Campus') );

insert into purchase (customer_id, painting_id, purchase_date, sales_price)
values ( (select customer_id from customer where name = 'Walt'), 
		 (select painting_id from painting where title = 'Tech Elevator Campus'), 
		 '2023-10-15', 1500.55);


