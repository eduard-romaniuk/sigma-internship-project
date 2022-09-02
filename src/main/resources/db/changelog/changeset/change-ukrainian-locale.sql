-- liquibase formatted sql
-- changeset Millrocious:change-ukrainian-locale

UPDATE locale
SET name = 'Ukrainian', iso_code = 'uk'
WHERE id = 1;