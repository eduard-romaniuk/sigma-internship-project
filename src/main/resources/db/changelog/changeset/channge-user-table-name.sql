-- liquibase formatted sql
-- changeset Millrocious:channge-user-table-name

ALTER TABLE "user"
    RENAME TO end_user;