package com.giczi.david.flight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.giczi.david.flight.domain.FlightTicket;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.repository.FlightTicketRepository;

@Service
public class FlightTicketService {

	
	private FlightTicketRepository ticketRepo;
	
	@Autowired
	public void setTicketRepo(FlightTicketRepository ticketRepo) {
		this.ticketRepo = ticketRepo;
	}
	
	
	public void saveFlightTicket(FlightTicket ticket) {
		
		if(ticket != null) {
			ticketRepo.save(ticket);
		}
		
	}

	public List<FlightTicket> findNotDeletedTicketsByPassengerId(Passenger passenger){
		return ticketRepo.findNotDeletedTicketsByUserId(passenger.getId());
	}

	
}
