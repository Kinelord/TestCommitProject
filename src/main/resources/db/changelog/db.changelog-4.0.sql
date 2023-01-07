--liquibase formatted sql

--changeset igor.shakirov:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset igor.shakirov:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);
