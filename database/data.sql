-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

insert into app_user (user_name, password, salt, role) values ('Kevin', 1, 2, 'admin');
insert into app_user (user_name, password, salt, role) values ('Kerry', 1, 2, 'admin');
insert into app_user (user_name, password, salt, role) values ('Brian', 1, 2, 'admin');
insert into app_user (user_name, password, salt, role) values ('John', 1, 2, 'coach');
insert into app_user (user_name, password, salt, role) values ('Steve', 1, 2, 'coach');
insert into app_user (user_name, password, salt, role) values ('Andrew', 1, 2, 'coach');
insert into app_user (user_name, password, salt, role) values ('Trey', 1, 2, 'client');
insert into app_user (user_name, password, salt, role) values ('Tim', 1, 2, 'client');
insert into app_user (user_name, password, salt, role) values ('Nick', 1, 2, 'client');

insert into clients (client_id, first_name, last_name) values (7, 'Trey', 'Tomlin');
insert into clients (client_id, first_name, last_name) values (8, 'Tim', 'Hart');
insert into clients (client_id, first_name, last_name) values (9, 'Nick', 'Miller');

insert into coaches (coach_id, first_name, last_name, city_location, state_location, about_me) 
values (4, 'John', 'Doe', 'Columbus', 'Ohio', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into coaches (coach_id, first_name, last_name, city_location, state_location, about_me) 
values (5, 'Steve', 'Jones', 'Hilliard', 'Ohio', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.');
insert into coaches (coach_id, first_name, last_name, city_location, state_location, about_me) 
values (6, 'Andrew', 'Frank', 'Dayton', 'Ohio', 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');

COMMIT;

select * from clients

