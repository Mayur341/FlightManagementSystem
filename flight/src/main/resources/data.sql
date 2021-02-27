insert into Passengers (first_name, last_name, date_of_birth, user_name, password, admin) values ('David', 'Giczi', '1978-07-12', 'david.giczi@gmail.com', 'Localhero78', true);

insert into Passengers (first_name, last_name, date_of_birth, user_name, password, admin) values ('Orbók', 'Anna', '1977-03-27', 'orboka@gmail.com', 'Tündérgalambocska79', false);

insert into Tickets (flight_number, departure_date, departure_place, arrival_date, arrival_place, passenger_id, price, deleted)
values('EF789', now(), 'Budapest', now(), 'Barcelona', (select id from Passengers where first_name = 'David' and last_name = 'Giczi'), 12500, false);


insert into Tickets (flight_number, departure_date, departure_place, arrival_date, arrival_place, passenger_id, price, deleted)
values('AF546', now(), 'Budapest', now(), 'Frankfurt', (select id from Passengers where first_name = 'Orbók' and last_name = 'Anna'), 18500, false);