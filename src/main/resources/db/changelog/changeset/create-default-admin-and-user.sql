-- liquibase formatted sql
-- changeset Millrocious:create-default-admin-and-user
INSERT INTO "user" (name, email, password, role, locale_id, subscription, update_date, create_date) VALUES
    ('admin', 'admin@email.com', '$2a$12$YCssqgHd6r5pe35ABugi1up4h26csHj6IKQqd5ILA9O8djDmNsL3a', 'ADMIN', 2, 'OFF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO "user" (name, email, password, role, locale_id, subscription, update_date, create_date) VALUES
    ('user', 'user@email.com', '$2a$12$sT0JcikHHA4gBE6kIprzf.XW3sFoUq/YugE8fWZzeuxSslP8clCju', 'USER', 2, 'OFF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
