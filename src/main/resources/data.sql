DELETE FROM app_user_roles;
DELETE FROM role;
DELETE FROM app_user;

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO app_user (id, username, password) VALUES
(1, 'admin', '$2a$10$Vsj5rkesZkuLqFWwdxFejO1FkGDgNn3DNZIC2tNUmXQ7P.299QgWG'), -- pass: 123
(2, 'user', '$2a$10$IB2BNXUNyM04Ryh6mL8.ReiMYXhltHfcw/u3QdQZngY/kOx5kACEm'); -- pass: 123

INSERT INTO app_user_roles (app_user_id, role_id) VALUES
(1, 1), -- admin → ROLE_ADMIN
(2, 2); -- user → ROLE_USER
