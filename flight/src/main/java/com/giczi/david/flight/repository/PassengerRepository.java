package com.giczi.david.flight.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.giczi.david.flight.domain.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
	
	List<Passenger> findAll();
	
}
