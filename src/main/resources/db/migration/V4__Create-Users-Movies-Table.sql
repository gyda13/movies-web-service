CREATE TABLE Users_Movies (
    user_id  INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (movie_id) REFERENCES Movies (movie_id)
);