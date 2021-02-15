INSERT INTO account (user_id, password) VALUES ('1001', '');
INSERT INTO account (user_id, password) VALUES ('1002', '');
INSERT INTO account (user_id, password) VALUES ('1003', '');

INSERT INTO user_profile (user_id, name) VALUES ('1001', 'todo-test1');
INSERT INTO user_profile (user_id, name) VALUES ('1002', 'todo-test2');
INSERT INTO user_profile (user_id, name) VALUES ('1003', 'todo-test3');

INSERT INTO todo (todo_id, text, completed, user_id) VALUES (2001, 'やるべきこと１', true, '1001');
INSERT INTO todo (todo_id, text, completed, user_id) VALUES (2002, 'やるべきこと２', false, '1001');

INSERT INTO todo (todo_id, text, completed, user_id) VALUES (2003, 'やるべきこと３', false, '1003');
