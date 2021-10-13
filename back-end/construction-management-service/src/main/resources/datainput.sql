create schema if not exists construction_management_service;
use construction_management_service;

insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("1","1", "Architect", "John Doe", "1955-01-01", "100000.00","5","johndoe101@gmail.com", "500-100-2000", "realjohndoe", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2017-05-27");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("2","1", "Employee", "Bob Builder", "1980-12-01", "50000.00","2","bobbuilder100@gmail.com", "123-450-2020", "bobbuilder", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-06-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("3","2", "Architect", "Abigail Lee", "1990-05-01", "120000.00","3","abigaillee@gmail.com", "907-221-2000", "realabigail", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2018-05-27");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("4","2", "Employee", "Bruce Wayne", "1970-11-05", "700000.00","5","imbatman@gmail.com", "999-100-2000", "batman", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2015-05-27");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("5","3", "Architect", "Pepper Pots", "1960-11-01", "70000000.00","1","pepperpots@gmail.com", "111-111-1111", "ironwoman", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2017-01-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("6","0", "Architect", "Michael Jordan", "1960-01-01", "7000.00","23","goat@gmail.com", "232-323-2323", "jordan23", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-01-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("7","0", "Architect", "Lebron James", "1980-01-01", "5000.00","24","kingjames@gmail.com", "342-242-2324", "lebron24", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2019-05-06");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("8","0", "Employee", "Worker Bee", "1990-02-21", "70500.00","7","buzzbuzz@gmail.com", "234-312-2423", "beebee", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2015-05-01");
insert into employee (id, project_id, title, name, date_of_birth, salary, years_of_experience, email, phone_number, username, password, user_since) values ("9","0", "Employee", "Jane Lane", "1971-03-11", "50500.00","10","janelane@gmail.com", "342-242-8888", "janelane", "$2a$10$LJzpQT87GlrepxwAEWoy5uj9hqEFx4GOqFo99e08rgEGvAZsrhbm2", "2018-12-06");



insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("1", "Jeff Bezo's Mansion", "2021-12-01", "2020-01-02", "Living Room", true, true, "10000000.00", "1000000.00", "1100000.00", "in_progress");
insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("2", "Bill Gate's Kitchen", "2021-12-05", "2020-12-07", "Kitchen", true, true, "500000.00", "10000.00", "510000.00", "completed");
insert into project (id, name, deadline, start_date, room_type, is_plumbing, is_electric, material_budget, labor_budget, total_budget, status) values ("3", "Tony Stark's Lab", "2022-06-01", "2019-01-01", "Lab", true, true, "1000000000.00", "10000000.00", "110000000.00", "in_progress");

insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("1", "1", "1", "Install Floor", "2021-12-06", "2021-12-09", "Install Marble Flooring", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("2", "1", "1", "Install Roof", "2020-06-20", "2020-06-27", "Install Roof", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("3", "2", "2", "Install Stove", "2021-12-06", "2021-12-09", "Install 2 stoves", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("4", "2", "3", "Install Cabinets", "2021-12-05", "2021-12-05", "Install mohagony cabinets", "completed");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("5", "3", "4", "Install Electrical Grid", "2022-02-06", "2022-06-12", "Install Electrical Grid to handle Arc Reactor", "in_progress");
insert into task (id, project_id, employee_id, name, start_date, deadline, description, status) values ("6", "3", "5", "Install Sprinkler System", "2022-01-01", "2022-02-09", "Install fire system", "in_progress");

select * from task;
select * from project;
select * from employee;