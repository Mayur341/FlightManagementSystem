package com.giczi.david.flight.service;

import java.util.List;
import java.util.Optional;
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
	
	public void cancelTicket(Long id) {
		
		Optional<FlightTicket> ticketOtional = ticketRepo.findById(id);
		FlightTicket ticket = ticketOtional.get();
		ticket.setDeleted(true);
		ticketRepo.save(ticket);
		
	}
	public List<FlightTicket> findByTextAndUserName(String text, Long id){
		
		return ticketRepo.findByTextAndUserName(text, id);
	}
}
