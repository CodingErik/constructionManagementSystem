drop schema construction_management_service;
create schema if not exists construction_management_service;
use construction_management_service;

-- create table employee(
-- 	id integer PRIMARY KEY AUTO_INCREMENT,
--     project_id INT,
--     name VARCHAR(100) NOT NULL,
--     username VARCHAR(100) NOT NULL UNIQUE,
-- 	email VARCHAR(100) NOT NULL UNIQUE,
--     password VARCHAR(100) NOT NULL,
--     title VARCHAR(100),
--     phone_number VARCHAR (100),
--     salary numeric,
--     date_of_birth date,
--     user_since date,
--     years_of_experience int
-- );

-- create table project(
-- 	id integer PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(100),
--     deadline date,
--     start_date date,
--     room_type VARCHAR(100),
--     is_plumbing boolean,
--     is_electric boolean,
--     material_budget numeric,
--     labor_budget numeric,
--     total_budget numeric,
--     status VARCHAR(100)
-- );

-- create table task(
-- 	id integer PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(100) Not null,
--     project_id int not null,
--     employee_id int,
--     start_date date,
--     deadline date,
--     description VARCHAR(100),
--     status VARCHAR(100)
--
-- );

insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("1","0", "admin", "admin", "1900-01-01", "100000.00","100","admin@gmail.com", "000-000-0000", "admin", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2000-01-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("2","1", "architect", "architect", "1980-12-01", "50000.00","50","architect@gmail.com", "123-450-2020", "architect", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-06-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("3","1", "employee", "employee", "1990-05-01", "120000.00","1","employee@gmail.com", "907-221-2000", "employee", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2018-05-27");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("4","2", "architect", "Bruce Wayne", "1970-11-05", "700000.00","5","batman@gmail.com", "999-100-2000", "batman", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2015-05-27");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("5","2", "employee", "Tony Stark", "1960-11-01", "70000000.00","1","ironman@gmail.com", "111-111-1111", "ironman", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2017-01-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("6","3", "architect", "Michael Jordan", "1960-01-01", "7000.00","23","goat@gmail.com", "232-323-2323", "jordan", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-01-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("7","4", "architect", "Clark Kent", "1980-01-01", "5000.00","24","superman@gmail.com", "342-242-2324", "superman", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-05-06");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("8","4", "employee", "Taylor Swift", "1990-02-21", "70500.00","7","taetae@gmail.com", "234-312-2423", "taylor", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2015-05-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("9","4", "employee", "Travis Scott", "1971-03-11", "50500.00","10","astroworld@gmail.com", "342-242-8888", "travis", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2018-12-06");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("10","4", "employee", "John Wick", "1971-03-11", "50500.00","10","boogeyman@gmail.com", "342-242-8888", "johnwick", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2018-12-06");


insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("1", "Jeff Bezo's Mansion", "2021-12-01", "2020-01-02", "Mansion", true, true, "10000000.00", "1000000.00", "1100000.00", "in_progress");
insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("2", "Tony Stark's Lab", "2021-12-05", "2020-02-07", "Lab", true, true, "500000.00", "10000.00", "510000.00", "cancelled");
insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("3", "Bill Gate's Kitchen", "2020-06-01", "2019-01-01", "Kitchen", false, true, "1000000000.00", "10000000.00", "110000000.00", "completed");
insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("4", "UCSD Hospital", "2025-06-01", "2020-01-01", "Hospital", true, true, "9000000.00", "999999.00", "9999999.00", "in_progress");

insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (1, 1, 3, "Floor", "2021-12-06", "2021-12-09", "Install Marble Flooring", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (2, 1, 3, "Roof", "2020-06-20", "2020-06-27", "Install Roof", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (3, 2, 5, "Electrical System", "2021-12-06", "2021-12-09", "Install Electrical System That Can 1000KwH", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (4, 2, 5, "Fire System", "2021-12-05", "2021-12-05", "Install Fire System with Sprinklers", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (5, 2, 5, "Arc Reactor", "2022-02-06", "2022-06-12", "Install Arc Reactor", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (6, 4, 8, "Wooden Shelves", "2022-01-01", "2022-02-09", "Build and Install Wooden Shelves", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (7, 4, 8, "Oxygen Pipes", "2022-01-01", "2022-02-09", "Install Oxygen Pipes", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (8, 4, 9, "Hospital Beds", "2022-01-01", "2022-02-09", "Buy Hospital Beds and Connect to Floor", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (9, 4, 10, "MRI Machine", "2022-01-01", "2022-02-09", "Move MRI Machine to Room", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values (10, 4, 10, "Advanced Floors", "2022-01-01", "2022-02-09", "Install Self Heating Floors", "cancelled");

select * from task;
select * from project;
select * from employee;