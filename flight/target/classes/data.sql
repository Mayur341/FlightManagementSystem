insert into Passengers (first_name, last_name, date_of_birth, user_name, password) values ('David', 'Giczi', '1978-07-12', 'david.giczi@gmail.com', 'Localhero78');
insert into Tickets (departure_date, departure_place, arrival_date, arrival_place, passenger_id, debit, paid)
values(now(), 'Budapest', now(), 'Barcelona', (select id from Passengers where first_name = 'David' and last_name = 'Giczi'), 12500, true);
insert into Tickets (departure_date, departure_place, arrival_date, arrival_place, passenger_id, debit, paid)
values(now(), 'Budapest', now(), 'Frankfurt', (select id from Passengers where first_name = 'David' and last_name = 'Giczi'), 18500, false);