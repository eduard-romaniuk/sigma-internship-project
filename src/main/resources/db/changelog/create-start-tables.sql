-- liquibase formatted sql
-- changeset Millrocious:create-table-statistic_data
CREATE TABLE IF NOT EXISTS statistic_data(
     id BIGSERIAL NOT NULL PRIMARY KEY,
     date_current DATE NOT NULL,
     day_current INTEGER NOT NULL,
     personal_units INTEGER NOT NULL,
     tanks INTEGER NOT NULL,
     armoured_fighting_vehicles INTEGER NOT NULL,
     artillery_systems INTEGER NOT NULL,
     mlrs INTEGER NOT NULL,
     aa_warfare_systems INTEGER NOT NULL,
     planes INTEGER NOT NULL,
     helicopters INTEGER NOT NULL,
     vehicles_fuel_tanks INTEGER NOT NULL,
     warships_cutters INTEGER NOT NULL,
     cruise_missiles INTEGER NOT NULL,
     uav_systems INTEGER NOT NULL,
     special_military_equip INTEGER NOT NULL,
     atgm_srbm_systems INTEGER NOT NULL
);

-- changeset Millrocious:create-table-fund
CREATE TABLE IF NOT EXISTS fund(
   id BIGSERIAL NOT NULL PRIMARY KEY,
   name varchar(255) NOT NULL,
   description text NOT NULL,
   link varchar(255) NOT NULL
);

-- changeset Millrocious:create-table-locale
CREATE TABLE IF NOT EXISTS locale(
     id BIGSERIAL NOT NULL PRIMARY KEY,
     name varchar(255) NOT NULL,
     iso_code varchar(2) NOT NULL
);

-- changeset Millrocious:create-table-user
CREATE TABLE IF NOT EXISTS "user"(
     id BIGSERIAL NOT NULL PRIMARY KEY,
     name varchar(255) NOT NULL,
     email varchar(255) NOT NULL,
     password varchar(255) NOT NULL,
     role varchar(255) NOT NULL,
     locale_id INTEGER NOT NULL REFERENCES locale (id),
     subscription varchar(255) NOT NULL
);
