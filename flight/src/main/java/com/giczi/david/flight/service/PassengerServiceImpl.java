package com.giczi.david.flight.service;

import java.util.Random;

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
	private EmailService emailService;
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

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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
		
		passengerToRegister.setEnabled(false);
		String activationKey = generatedKey();
		passengerToRegister.setActivation(activationKey);
		emailService.sendMeassage(passengerToRegister.getUserName(), passengerToRegister.getFirstName(), passengerToRegister.getLastName(), activationKey);
		passengerToRegister.setPassword(EncoderService.passwordEncoder().encode(passengerToRegister.getPassword()));
		passengerRepo.save(passengerToRegister);
		
		return true;
	}

	
	private String generatedKey() {
		
		Random random = new Random();
		char [] word = new char[16];
		for(int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		
		return new String(word);
	}

	@Override
	public boolean userActivation(String code) {
		
		Passenger passenger = passengerRepo.findByActivation(code);
		
		if(passenger == null) {
			return false;
		}
		
		passenger.setActivation("");
		passenger.setEnabled(true);
		passengerRepo.save(passenger);
		
		return true;
	}
	
	
	
}
