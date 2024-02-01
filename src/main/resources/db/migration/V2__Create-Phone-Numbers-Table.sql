CREATE TABLE Phone_Numbers (
    id            varchar(255) PRIMARY KEY,
    user_id       varchar(255)  NOT NULL,
    mobile_number varchar NOT NULL,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES Users(id)
);