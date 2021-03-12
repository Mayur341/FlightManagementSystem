package com.giczi.david.flight.service;

import com.giczi.david.flight.domain.Passenger;

public interface PassengerService {

	
	Passenger findByUserName(String username);
	boolean registerPassenger(Passenger passengerToRegister);
}
