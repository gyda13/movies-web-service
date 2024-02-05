CREATE TABLE Phone_Numbers (
    id            serial PRIMARY KEY,
    user_id       INTEGER  NOT NULL,
    mobile_number varchar NOT NULL,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES Users(id)
);