use notes;

INSERT INTO RF_ROLE (ID, ROLE)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO RF_ROLE (ID, ROLE)
VALUES (2, 'ROLE_USER');

INSERT INTO USER (EMAIL, PASSWORD, ROLE, CREATION_DATE, LAST_MODIFICATION_DATE)
VALUES ('admin@disqo.com', '$2a$11$U25ghOtBB9dIUfxRB6s5uu52IvRtN3BQbQyPgQk70ddVkAwfO52fm', 1, now(), now());

INSERT INTO NOTE (TITLE, CONTENT, CREATION_DATE, LAST_MODIFICATION_DATE, USER_ID)
VALUES ('First Note', 'Content', now(), now(), 1);
