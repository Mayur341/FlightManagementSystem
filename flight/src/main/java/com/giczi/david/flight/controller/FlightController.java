package com.giczi.david.flight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.service.FlightService;
import com.giczi.david.flight.service.PassengerService;

import org.slf4j.Logger;



@Controller
@RequestMapping("/flight")
public class FlightController {

	private FlightService flightService;
	private PassengerService passengerService;
	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	public FlightController(FlightService service, PassengerService passengerService) {
		this.flightService = service;
		this.passengerService = passengerService;
	}
	
	@RequestMapping("/reservations")
	public String showAllPassengers(Model model) {
		
		model.addAttribute("passengers", flightService.getAllData());
			
		return "reservations";
	}
	
	@RequestMapping("/order")
	public String goOrderPage(Authentication auth) {
		
		System.out.println(auth.getName());
		
		return "order";
	}
	
	@RequestMapping("/registration")
	public String goRegistrationPage(Model model) {
		
		model.addAttribute("user", new Passenger());
		
		return "regist";
	}
	
	
	@PostMapping("/reg")
	public String greetingSubmit(@ModelAttribute Passenger user) {
		
		passengerService.registerPassenger(user);
			
		
		return "auth/login";
	}
	
}
