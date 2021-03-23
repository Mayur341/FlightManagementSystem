package com.giczi.david.flight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.giczi.david.flight.domain.FlightTicket;
import com.giczi.david.flight.domain.Passenger;
import com.giczi.david.flight.service.FlightTicketService;
import com.giczi.david.flight.service.PassengerService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;



@Controller
@RequestMapping("/flight")
public class FlightController {

	private PassengerService passengerService;
	private FlightTicketService ticketService;
	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	private List<String> departurePlaces;
	private List<String> arrivalPlaces;
	private List<String> planes;
	
	
	@Autowired
	public FlightController(PassengerService passengerService, FlightTicketService ticketService) {
		this.passengerService = passengerService;
		this.ticketService = ticketService;
	}
	
	@RequestMapping("/reservations")
	public String showAllTickets(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Passenger passenger = passengerService.findByUserName(currentPrincipalName);
		List<FlightTicket> tickets = ticketService.findNotDeletedTicketsByPassengerId(passenger);
		
		model.addAttribute("orderedTickets", tickets);
			
		return "reservations";
	}
	
	@RequestMapping("/order")
	public String goOrderPage(Model model) {
		init(); //for demo only
		model.addAttribute("departurePlaces", departurePlaces);
		model.addAttribute("arrivalPlaces", arrivalPlaces);
		model.addAttribute("planes", planes);
		model.addAttribute("cost", (int)(Math.random() * 20 + 1) * 500 + 10000);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
		model.addAttribute("ticket", new FlightTicket());
		 
		return "order";
	}
	
	@RequestMapping("/reserve")
	public String goOrderPage(@ModelAttribute FlightTicket ticket) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Passenger passenger = passengerService.findByUserName(currentPrincipalName);
		ticket.setPassenger(passenger);
		ticketService.saveFlightTicket(ticket);
		
		return "redirect:/flight/reservations";
	}
	
	@RequestMapping("/cancel{id}")
	public String cancelReservation(@RequestParam(value = "id") Long id) {
		
		ticketService.cancelTicket(id);
		
		return "redirect:/flight/reservations";
	}
	
	
	@RequestMapping("/search{text}")
	public String search(@RequestParam(value = "text") String text, Model model) {
		
		if( !text.isEmpty()) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Passenger passenger =  passengerService.findByUserName(currentPrincipalName);
		List<FlightTicket> tickets = ticketService.findByTextAndUserName(text, passenger.getId());
		model.addAttribute("txt", text);
		model.addAttribute("orderedTickets", tickets);
		}
		
		return "reservations";
	}
	
	@RequestMapping("/registration")
	public String goRegistrationPage(Model model) {
		
		model.addAttribute("user", new Passenger());
		
		return "regist";
	}
	
	
	@PostMapping("/reg")
	public String registration(@ModelAttribute Passenger user, RedirectAttributes attribute) {
		
		if(!passengerService.registerPassenger(user)) {
			
			attribute.addAttribute("exists", true);
			
			return "redirect:/flight/registration";
		}
			
		
		return "auth/login";
	}
	
	private void init() {
		
		departurePlaces = new ArrayList<>();
		departurePlaces.add("Budapest");
		departurePlaces.add("Debrecen");
		arrivalPlaces = new ArrayList<>();
		arrivalPlaces.add("Prague");
		arrivalPlaces.add("Berlin");
		arrivalPlaces.add("Barcelona");
		arrivalPlaces.add("London");
		arrivalPlaces.add("Paris");
		arrivalPlaces.add("Helsinki");
		arrivalPlaces.add("Moscow");
		planes = new ArrayList<>();
		planes.add("NKS-137");
		planes.add("THY-1G6");
		planes.add("N-X-211");
	}
	
}
