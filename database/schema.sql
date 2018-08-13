-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS availability;
DROP TABLE IF EXISTS coaches;
DROP TABLE IF EXISTS app_user;


CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

CREATE TABLE coaches (
  coaches_id INT NOT NULL,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  about_me text NOT NULL,
  
  CONSTRAINT pk_coaches_id PRIMARY KEY(coaches_id),
  CONSTRAINT fk_coaches_id FOREIGN KEY(coaches_id) REFERENCES app_user(id)
);

CREATE TABLE availability (
  availability_id SERIAL PRIMARY KEY,
  coaches_id INT NOT NULL,
  day_of_week varchar(9),
  hour_of_day INT,
  
  CONSTRAINT fk_coaches_id FOREIGN KEY(coaches_id) REFERENCES coaches(coaches_id),
  CONSTRAINT hour_of_day_0_to_24 CHECK ((hour_of_day <= 23) AND (hour_of_day >= 0))
);



COMMIT;