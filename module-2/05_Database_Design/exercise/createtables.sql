create table Members (
		member_number int primary key,
		last_name varchar(50) not null,
		first_name varchar(50) not null,
		email varchar(100) not null,
		phone_number varchar(20),
		date_of_birth date not null,
		email_reminder boolean not null
);

create table InterestGroups (
		group_number int primary key,
		name varchar(50) not null unique
	
);

create table Events (
		event_number int primary key,
		name varchar(100) not null,
		description text,
		start_date_time date not null,
		duration_in_minutes int check
	(duration_in_minutes >= 30) not null,
		group_number int not null,
	foreign key (group_number) references InterestGroups (group_number)

);
create table MemberInterestGroups (
		member_number int,
		group_number int,
		primary key (member_number, group_number),
		foreign key (member_number) references Members (member_number),
		foreign key (group_number) references InterestGroups (group_number)

);
create table MemberEvents (
		member_number int,
		event_number int,
		primary key (member_number, event_number),
		foreign key (member_number) references Members (member_number),
		foreign key (event_number) references Events (event_number)
);


