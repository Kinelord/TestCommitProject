--liquibase formatted sql

--changeset igor.shakirov:1
CREATE TABLE IF NOT EXISTS company
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);
--rollback DROP TABLE company;


--changeset igor.shakirov:2
CREATE TABLE IF NOT EXISTS company_locales
(
    company_id INT REFERENCES company (id) ON DELETE CASCADE,
    lang VARCHAR(2),
    description VARCHAR(255) NOT NULL ,
    PRIMARY KEY (company_id, lang)
);
--rollback DROP TABLE company_locales;

--changeset igor.shakirov:3
CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    company_id INT REFERENCES company (id)
);
--rollback DROP TABLE users;

--changeset igor.shakirov:4
CREATE TABLE IF NOT EXISTS payment
(
    id BIGSERIAL PRIMARY KEY ,
    amount INT NOT NULL ,
    receiver_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE
);
--rollback DROP TABLE payment;

--changeset igor.shakirov:5
CREATE TABLE IF NOT EXISTS chat
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);
--rollback DROP TABLE chat;

--changeset igor.shakirov:6
CREATE TABLE IF NOT EXISTS users_chat
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    chat_id BIGINT NOT NULL REFERENCES chat (id) ON DELETE CASCADE,
    UNIQUE (user_id, chat_id)
);
--rollback DROP TABLE users_chat;