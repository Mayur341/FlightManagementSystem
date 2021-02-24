package com.giczi.david.flight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.giczi.david.flight.service.FlightService;


@Controller
public class FlightController {

	private FlightService service; 
	
	@Autowired
	public void setService(FlightService service) {
		this.service = service;
	}

	@RequestMapping("/orders")
	public String showAllPassengers(Model model) {
	
		 
		model.addAttribute("passengers", service.getAllData());
		
		
		return "orders";
	}
	
	
	@RequestMapping("/flight")
	public String startPage() {
		return "layouts/start";
	}
	
}
