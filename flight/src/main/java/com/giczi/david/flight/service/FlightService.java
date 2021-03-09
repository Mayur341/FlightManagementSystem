package com.giczi.david.flight.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.giczi.david.flight.domain.FlightData;
import com.giczi.david.flight.domain.FlightTicket;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.repository.FlightTicketRepository;
import com.giczi.david.flight.repository.PassengerRepository;


@Service
public class FlightService   {

	
	private PassengerRepository passengerRepo;
	private FlightTicketRepository flightTicketRepo;
	
	@Autowired
	public void setPassengerRepo(PassengerRepository passengerRepo) {
		this.passengerRepo = passengerRepo;
	}
	
	
	@Autowired
	public void setFlightTicketRepo(FlightTicketRepository flightTicketRepo) {
		this.flightTicketRepo = flightTicketRepo;
	}


	public List<FlightData> getAllData(){
		List<FlightData> allData = new ArrayList<>();
		List<FlightTicket> tickets = flightTicketRepo.findAll();
		for (FlightTicket flightTicket : tickets) {
			Passenger passenger = flightTicket.getPassenger();
			if(passenger != null) {
				FlightData data = new FlightData(passenger);
				data.setFlightNumber(flightTicket.getFlightNumber());
				data.setDepartureDate(flightTicket.getDepartureDate());
				data.setDeparturePlace(flightTicket.getDeparturePlace());
				data.setArrivalDate(flightTicket.getArrivalDate());
				data.setArrivalPlace(flightTicket.getArrivalPlace());
				data.setPrice(flightTicket.getPrice());
				data.setDeleted(flightTicket.isDeleted());
				allData.add(data);
			}
		}
		
		return allData;
	}
	
	public String encodePassword(String password) {
		return Base64Utils.encodeToString(password.getBytes());
	}
	
	public String decodePassword(String decodedPassword) {
		return new String(Base64Utils.decodeFromString(decodedPassword));
	}
	
	public void savePassenger(Passenger passenger) {
		passenger.setPassword(encodePassword(passenger.getPassword()));
		passengerRepo.save(passenger);
	}
	
	public Passenger findById(Long id) {
	Optional<Passenger> passenger = passengerRepo.findById(id);
	passenger.get().setPassword(decodePassword(passenger.get().getPassword()));
	return passenger.get();
	}
		
}
