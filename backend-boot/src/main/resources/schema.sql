DROP TABLE IF EXISTS todo;
CREATE TABLE todo
(
    todo_id   serial      NOT NULL,
    text      VARCHAR(20) NOT NULL,
    completed BOOLEAN     NOT NULL,
    user_id   VARCHAR(40) NOT NULL,
    PRIMARY KEY (todo_id)
);

DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    user_id   VARCHAR(40) NOT NULL,
    password  VARCHAR(256) NOT NULL,
    PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile
(
    user_id   VARCHAR(40) NOT NULL,
    name      VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id)
    --FOREIGN KEY (user_id) REFERENCES account (user_id)
);
