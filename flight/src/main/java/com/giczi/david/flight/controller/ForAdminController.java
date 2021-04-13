package com.giczi.david.flight.controller;


import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.service.FlightTicketService;
import com.giczi.david.flight.service.PassengerService;

@Controller
@RequestMapping("/admin")
public class ForAdminController {

	
	private PassengerService passengerService;
	private FlightTicketService ticketService;

 

	@Autowired
	public void setPassengerService(PassengerService passengerService) {
		this.passengerService = passengerService;
	}
	

	@Autowired
	public void setTicketService(FlightTicketService ticketService) {
		this.ticketService = ticketService;
	}




	@RequestMapping("/clients")
	public String goClientsPage(Model model) {
		
		model.addAttribute("clients", passengerService.findAll());
		
		return "clients";
	}
	
	@RequestMapping("enter")
	public String enterUserAccount(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
		
		Optional<Passenger> passenger = passengerService.findPassengerById(id); 

		UsernamePasswordAuthenticationToken token = 
	            new UsernamePasswordAuthenticationToken(passenger.get().getUserName(), passenger.get().getPassword());
	    token.setDetails(new WebAuthenticationDetails(request));
	    AuthenticationProvider authenticationProvider = new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> authentication) {
				
				return authentication.equals(UsernamePasswordAuthenticationToken.class);
			}
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				
				String username = authentication.getName();
		        String password = authentication.getCredentials()
		            .toString();
		        
				return new UsernamePasswordAuthenticationToken
			              (username, password, Collections.emptyList());
			}
		};
		
		Authentication auth = authenticationProvider.authenticate(token);
		
	    SecurityContextHolder.getContext().setAuthentication(auth);	
		
		return "redirect:/flight/order";
	}
	
	@RequestMapping("enabled")
	public String enabledUserAccount(@RequestParam("id") Long id) {
		
		Optional<Passenger> passenger = passengerService.findPassengerById(id);
		
		if(passenger.isPresent()) {
			if(passenger.get().isEnabled()) {
				passenger.get().setEnabled(false);
			}
			else {
				passenger.get().setEnabled(true);
			}
			
			passengerService.save(passenger.get());
			
		}
		return "redirect:/admin/clients";
	}
	
	@RequestMapping("role")
	public String setRole(@RequestParam("id") Long id,@RequestParam("role") String role) {
		
		Optional<Passenger> passenger = passengerService.findPassengerById(id);
		
		if(passenger.isPresent()) {
			passenger.get().getRoles().clear();
			passenger.get().addRoles(role);
			passengerService.save(passenger.get());
		}
		
		return "redirect:/admin/clients";
	}
	
	@RequestMapping("delete")
	public String deleteUserAccount(@RequestParam("id") Long id) {
		
		Optional<Passenger> passenger = passengerService.findPassengerById(id);
		
		if(passenger.isPresent()) {
		passenger.get().setRoles(null);	
		ticketService.deleteAllPassengerTickets(id);
		passengerService.delete(passenger.get());
		}
		
		return "redirect:/admin/clients";
	}
}
