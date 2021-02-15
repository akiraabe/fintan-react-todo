CREATE TABLE IF NOT EXISTS todo
(
    todo_id   serial      NOT NULL,
    text      VARCHAR(20) NOT NULL,
    completed BOOLEAN     NOT NULL,
    user_id   VARCHAR(40) NOT NULL,
    PRIMARY KEY (todo_id)
);