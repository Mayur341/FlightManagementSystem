package com.giczi.david.flight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.giczi.david.flight.domain.FlightTicket;

@Repository
public interface FlightTicketRepository extends CrudRepository<FlightTicket, Long> {
	
	List<FlightTicket> findAll();
	List<FlightTicket> findByPassengerId(Long id);
	@Query(value = "select * from tickets where passenger_id = :id and deleted = false", nativeQuery = true)
	List<FlightTicket> findNotDeletedTicketsByUserId(@Param("id") Long id);
}
