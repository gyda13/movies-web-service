CREATE TABLE Favorites (
    id          serial PRIMARY KEY,
    movie_id    varchar NOT NULL,
    movie_title varchar NOT NULL
);