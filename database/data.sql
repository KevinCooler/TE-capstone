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

insert into availability (coach_id, day_of_week, hour_start, hour_end) values (4, 1, 7, 10);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (4, 3, 14, 16);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (4, 5, 7, 10);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (4, 5, 11, 12);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (4, 6, 13, 20);

insert into availability (coach_id, day_of_week, hour_start, hour_end) values (5, 2, 8, 13);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (5, 4, 8, 13);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (5, 6, 8, 13);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (5, 1, 7, 9);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (5, 1, 11, 13);

insert into availability (coach_id, day_of_week, hour_start, hour_end) values (6, 3, 18, 22);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (6, 4, 8, 18);
insert into availability (coach_id, day_of_week, hour_start, hour_end) values (6, 5, 8, 13);

insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (4, 7, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Tellus orci ac auctor augue.', 5, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (4, 8, 'Proin fermentum leo vel orci.', 3, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (4, 9, 'Eget sit amet tellus cras adipiscing enim eu turpis egestas. Arcu dui vivamus arcu felis bibendum ut tristique et egestas. Porttitor massa id neque aliquam vestibulum.', 3, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (5, 9, 'Velit euismod in pellentesque massa.', 5, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (5, 9, 'Duis at tellus at urna condimentum mattis pellentesque.', 1, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (6, 8, 'Nulla posuere sollicitudin aliquam ultrices sagittis orci. Sagittis orci a scelerisque purus semper. Dui vivamus arcu felis bibendum ut tristique. Eget arcu dictum varius duis at consectetur lorem.', 2, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (6, 7, 'Tellus pellentesque eu tincidunt tortor aliquam. Viverra nibh cras pulvinar mattis nunc. Ante metus dictum at tempor commodo ullamcorper a lacus vestibulum.', 5, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (6, 7, 'Ac tincidunt vitae semper quis. Tellus elementum sagittis vitae et leo duis ut diam.', 5, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (6, 9, 'Id eu nisl nunc mi ipsum faucibus vitae aliquet nec.', 4, NOW());
insert into coach_reviews (coach_id, client_id, review_text, rating, create_date) values (6, 8, '', 5, NOW());

COMMIT;


