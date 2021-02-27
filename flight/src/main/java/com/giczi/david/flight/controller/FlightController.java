package com.giczi.david.flight.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/login")
	public String goLoginPage() {
		
		return "layouts/login";
	}
	
	@RequestMapping("/registration")
	public String goRegistrationPage() {
		
		return "regist";
	}
	
	@RequestMapping("/")
	public String changeFlightPageLanguage() {
		
		
		return "layouts/login";
	}
	
	
}
