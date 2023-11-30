
drop table if exists employee;
drop table if exists department;

create table department 
(
  department_id serial primary key,
  name varchar(25) null
);

create table employee
(
   employee_id serial primary key,
   last_name varchar(20) null,
   department_id int4 references department (department_id)
);

insert into department 
(name)
values ('Clerical'), ('Engineering'), ('Sales'), ('Marketing');

insert into employee
(last_name, department_id)
values ('Smith', 1), ('Jones', 2), ('Robinson', 1), ('Williams', NULL), ('Heisenberg', 2), ('Rafferty', 3);



-----------------------------------------------------------------------------------------------

-- JOIN EXAMPLES


-- Select departments and employees that have a department_id match,
-- all departments that don't have an employee,
-- and all employees that don't have a department_id
select * 
from employee 
full join department on employee.department_id = department.department_id;

-- Select departments and employees that have a department_id match,
-- and all employees that don't have a department_id
select * 
from employee 
left join department on employee.department_id = department.department_id;

-- Select all employees that don't have a department_id
select * 
from employee 
left join department on employee.department_id = department.department_id 
where department.department_id is null;

-- Select departments and employees that have a department_id match,
-- and all departments that don't have a employee
select * 
from employee 
right join department on employee.department_id = department.department_id;

-- Select all departments that don't have a employee
select * 
from employee 
right join department on employee.department_id = department.department_id 
where employee.department_id is null;

-- Select departments and employees that have a department_id match,
select * 
from employee 
inner join department on employee.department_id = department.department_id;






