CREATE TABLE Phone_Numbers (
    id            serial PRIMARY KEY,
    user_id       INT     NOT NULL,
    mobile_number varchar NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id)
);