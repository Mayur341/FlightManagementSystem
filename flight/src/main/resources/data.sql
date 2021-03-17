INSERT INTO passengers (first_name, last_name, date_of_birth, user_name, password) VALUES ('Dávid', 'Giczi', '1978-07-12', 'admin', '$2a$10$cMKehzeSSEyrex35WuArNuCjlmE0sxXlOcOyMwGm2PWtokHan4UOG');
INSERT INTO roles (id, role) VALUES (1, 'ADMIN');
INSERT INTO passengers_roles (passenger_id, role_id) VALUES (1,1);