CREATE TABLE Users_Favorites (
    id       serial PRIMARY KEY,
    user_id  INT NOT NULL,
    movie_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (movie_id) REFERENCES Favorites (id)
);