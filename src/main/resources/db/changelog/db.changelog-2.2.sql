--liquibase formatted sql

--changeset igor.shakirov:1
ALTER TABLE users_aud
drop constraint users_aud_username_key;

ALTER TABLE users_aud
ALTER COLUMN username DROP NOT NULL;
