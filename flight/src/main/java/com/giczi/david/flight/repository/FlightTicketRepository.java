package com.giczi.david.flight.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.giczi.david.flight.domain.FlightTicket;

public interface FlightTicketRepository extends CrudRepository<FlightTicket, Long> {
	
	List<FlightTicket> findAll();
}
