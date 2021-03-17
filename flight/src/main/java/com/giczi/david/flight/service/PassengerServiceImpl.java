package com.giczi.david.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.domain.Role;
import com.giczi.david.flight.repository.PassengerRepository;
import com.giczi.david.flight.repository.RoleRepository;

@Service
public class PassengerServiceImpl implements PassengerService, UserDetailsService {
	
	private PassengerRepository passengerRepo;
	private RoleRepository roleRepo;
	private final String USER_ROLE = "USER";
	private final String ADMIN_ROLE = "ADMIN_ROLE";
	
	@Autowired
	public void setPassengerRepo(PassengerRepository passengerRepo) {
		this.passengerRepo = passengerRepo;
	}
	
	@Autowired
	public void setRoleRepo(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Passenger passenger = findByUserName(username);
		
		if(passenger == null) {
			throw new UsernameNotFoundException(username);
		}	
		
		return new PassengerDetailsImpl(passenger);
	}

	@Override
	public Passenger findByUserName(String username) {
		return passengerRepo.findByUserName(username);
	}

	@Override
	public boolean registerPassenger(Passenger passengerToRegister) {
		
		Passenger passengerCheck = passengerRepo.findByUserName(passengerToRegister.getUserName());
		
		if(passengerCheck != null) {
			return false;
		}
		
		Role userRole = roleRepo.findByRole(USER_ROLE);
		
		if(userRole != null) {
			passengerToRegister.getRoles().add(userRole);
		}
		else {
			passengerToRegister.addRoles(USER_ROLE);
		}
		
		passengerToRegister.setPassword(EncoderService.passwordEncoder().encode(passengerToRegister.getPassword()));
		
		passengerRepo.save(passengerToRegister);
		
		return true;
	}

	
}
