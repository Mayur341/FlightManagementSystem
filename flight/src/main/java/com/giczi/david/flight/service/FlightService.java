package com.giczi.david.flight.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
				data.setDepartureDate(flightTicket.getDepartureDate());
				data.setDeparturePlace(flightTicket.getDeparturePlace());
				data.setArrivalDate(flightTicket.getArrivalDate());
				data.setArrivalPlace(flightTicket.getArrivalPlace());
				data.setDebit(flightTicket.getDebit());
				data.setPaid(flightTicket.isPaid());
				allData.add(data);
			}
		}
		
		return allData;
	}
	
	
	
}
