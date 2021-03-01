package com.giczi.david.flight.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.giczi.david.flight.domain.FlightTicket;

@Repository
public interface FlightTicketRepository extends CrudRepository<FlightTicket, Long> {
	
	List<FlightTicket> findAll();
}
