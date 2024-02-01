CREATE TABLE Users_Favorites (
    id       varchar(255) PRIMARY KEY,
    user_id  varchar(255) NOT NULL,
    movie_id varchar(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (movie_id) REFERENCES Favorites (id)
);