-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS coach_reviews;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS availability;
DROP TABLE IF EXISTS coaches;
DROP TABLE IF EXISTS app_user;


CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32) DEFAULT 'client',
  salt varchar(255)
);

CREATE TABLE coaches (
  coach_id INT PRIMARY KEY,
  first_name varchar(45),
  last_name varchar(45),
  city_location varchar(45),
  state_location varchar(45),
  about_me text,
  
  CONSTRAINT fk_coach_id FOREIGN KEY(coach_id) REFERENCES app_user(id)
);

CREATE TABLE clients (
  client_id INT PRIMARY KEY,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  is_looking_for_coach boolean,
  city_location varchar(45),
  state_location varchar(45),
  about_me text,
  
  CONSTRAINT fk_client_id FOREIGN KEY(client_id) REFERENCES app_user(id)
);

CREATE TABLE availability (
  availability_id SERIAL PRIMARY KEY,
  coach_id INT NOT NULL,
  day_of_week INT,
  hour_start INT,
  hour_end INT,
  
  CONSTRAINT fk_coach_id FOREIGN KEY(coach_id) REFERENCES coaches(coach_id),
  CONSTRAINT hour_start_0_to_24 CHECK ((hour_start <= 23) AND (hour_start >= 0)),
  CONSTRAINT hour_end_0_to_24 CHECK ((hour_end <= 23) AND (hour_end >= 0)),
  CONSTRAINT day_between_0_and_6 CHECK ((day_of_week <= 7) AND (day_of_week >= 1))
);

CREATE TABLE coach_reviews (
  id SERIAL PRIMARY KEY,
  coach_id INT NOT NULL,
  client_id INT NOT NULL,
  review_text TEXT,
  rating INT NOT NULL, 
  create_date TIMESTAMP NOT NULL,
  
  CONSTRAINT fk_coach_id FOREIGN KEY(coach_id) REFERENCES coaches(coach_id)
  --CONSTRAINT fk_client_id FOREIGN KEY(client_id) REFERENCES clients(client_id)
);

COMMIT;