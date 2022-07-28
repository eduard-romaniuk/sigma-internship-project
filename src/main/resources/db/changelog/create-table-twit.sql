-- liquibase formatted sql
-- changeset eduard-romaniuk:create-table-twit
CREATE TABLE IF NOT EXISTS twit(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    author varchar(50) NOT NULL,
    "text" varchar(255) NOT NULL
);
