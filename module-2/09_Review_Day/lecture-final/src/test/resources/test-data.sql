START TRANSACTION;

CREATE TABLE pet
(
	pet_id serial,
	pet_name varchar(200) not null,
	pet_type varchar(100) not null,
	pet_birthdate timestamp,
	owner varchar(200),

	primary key (pet_id)
);

CREATE TABLE pet_visit
(
	pet_id int,
	visitdate timestamp,
	procedure_no int,

	primary key (pet_id, visitdate, procedure_no)
);


CREATE TABLE pet_procedures(
	procedure_no serial,
	procedure_name varchar(200),

	primary key (procedure_no)
);

ALTER TABLE pet
ADD CONSTRAINT chk_pet_type CHECK (pet_type IN ('Dog', 'Bird', 'Cat', 'Reptile', 'Fish', 'Rodent'));


ALTER TABLE pet_visit
ADD FOREIGN KEY (pet_id) REFERENCES pet(pet_id);


ALTER TABLE pet_visit
ADD FOREIGN KEY (procedure_no) REFERENCES pet_procedures(procedure_no);

----------------------------------

INSERT INTO pet (pet_id, pet_name, pet_type, pet_birthdate, owner)
VALUES (246, 'Rover', 'Dog', '2011-10-20', 'Sam Cook'),
       (298, 'Spot', 'Dog', '2021-04-01', 'Terry Kim'),
       (341, 'Morris', 'Cat', '2019-05-30', 'Sam Cook'),
       (519, 'Tweedy', 'Bird', '2021-01-15', 'Terry Kim');

INSERT INTO pet_procedures (procedure_no, procedure_name)
VALUES (1, 'Rabies Vaccination'),
       (5, 'Heart Worm Test'),
       (8, 'Tetanus Vaccination'),
       (10, 'Examine and Treat Wound');

INSERT INTO pet_visit (pet_id, visitdate, procedure_no)
VALUES (246, '2002-01-13', 1),
       (246, '2002-03-27', 10),
       (246, '2002-04-02', 5),
       (298, '2002-01-21', 8),
       (298, '2002-03-10', 5);

COMMIT;