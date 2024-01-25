CREATE TABLE Users (
    id       serial PRIMARY KEY,
    username varchar NOT NULL,
    password varchar NOT NULL,
    email    varchar NOT NULL
);
