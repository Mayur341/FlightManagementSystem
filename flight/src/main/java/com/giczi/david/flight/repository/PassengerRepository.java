package com.giczi.david.flight.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giczi.david.flight.domain.Passenger;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
	
	List<Passenger> findAll();
	@Query(value = "select * from passengers where user_name = :user and password = :pass", nativeQuery = true)
	Passenger findPassengerByUsernameAndPassword(@Param("user") String user, @Param("pass") String pass);
	Passenger findByUserName(String username);
	Passenger findByActivation(String code);
	
}
