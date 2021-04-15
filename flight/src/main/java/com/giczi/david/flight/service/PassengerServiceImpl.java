package com.giczi.david.flight.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.domain.PassengerDAO;
import com.giczi.david.flight.domain.Role;
import com.giczi.david.flight.repository.PassengerRepository;
import com.giczi.david.flight.repository.RoleRepository;

@Service
public class PassengerServiceImpl implements PassengerService, UserDetailsService {
	
	private PassengerRepository passengerRepo;
	private RoleRepository roleRepo;
	private EmailService emailService;
	public static String USER_ROLE = "ROLE_USER";
	public static String ADMIN_ROLE = "ROLE_ADMIN";
	public static String GUEST_ROLE = "ROLE_GUEST";
	
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
		
		Passenger passenger = passengerRepo.findByUserName(username);
		
		if(passenger == null) {
			throw new UsernameNotFoundException(username);
		}	
		
		return new PassengerDetailsImpl(passenger);
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
		//passengerToRegister.setPassword(EncoderService.getBCryptEncoder().encode(passengerToRegister.getPassword()));
		passengerToRegister.setPassword(EncoderService.encodeByBase64(passengerToRegister.getPassword()));
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
		
		passenger.setActivation(null);
		passenger.setEnabled(true);
		passengerRepo.save(passenger);
		
		return true;
	}

	@Override
	public String getRoleByUsername(String username) {
		
		Passenger passenger = passengerRepo.findByUserName(username);
		
		if(passenger.getRoles().contains(new Role(GUEST_ROLE))) {
			return GUEST_ROLE;
		}
		else if(passenger.getRoles().contains(new Role(ADMIN_ROLE))) {
			return ADMIN_ROLE;
		}
		
		return USER_ROLE;
	}

	@Override
	public Passenger findPassengerByUserName(String username) {
		return passengerRepo.findByUserName(username);
	}

	@Override
	public List<PassengerDAO> findAll() {
		
		List<Passenger> clients = passengerRepo.findAll();
		List<PassengerDAO> clientsDAO = new ArrayList<>();
		
		for (Passenger passenger : clients) {
			clientsDAO.add(new PassengerDAO(passenger.getId(),
											passenger.getFirstName(),
											passenger.getLastName(),
											passenger.getDateOfBirth(),
											passenger.getUserName(),
											passenger.getPassword(),
											passenger.getActivation(),
											getRoleByUsername(passenger.getUserName()),
											passenger.isEnabled()));
		}
		
		return clientsDAO;
	}

	@Override
	public Optional<Passenger> findPassengerById(Long id) {
		
		return passengerRepo.findById(id);
	}

	@Override
	public void save(Passenger passenger) {
		
		passengerRepo.save(passenger);
		
	}

	@Override
	public void delete(Passenger passenger) {
		passengerRepo.delete(passenger);
	}
	
	
	
}
