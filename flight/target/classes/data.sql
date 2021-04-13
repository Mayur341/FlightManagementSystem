INSERT INTO passengers (first_name, last_name, date_of_birth, user_name, password, enabled) VALUES ('Dávid', 'Giczi', '1978-07-12', 'admin', 'YWRtaW4=', true);
INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO passengers_roles (passenger_id, role_id) VALUES (1,1);
