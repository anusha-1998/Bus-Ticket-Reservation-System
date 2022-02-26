package com.example.BusTicketReservationSystem.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BusTicketReservationSystem.model.Passenger;
import com.example.BusTicketReservationSystem.service.PassengerService;


@Controller
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewPassengerForm")
	public String showNewPassengerForm(Model model) {
		// create model attribute to bind form data
		Passenger passenger = new Passenger();
		model.addAttribute("passenger", passenger);
		return "new_passenger";
	}
	
	@PostMapping("/savePassenger")
	public String savePassenger(@ModelAttribute("passenger") Passenger passenger) {
		passengerService.savePassenger(passenger);
		//List<String>sourceList = Arrays.asList("Hyderabad","Andhra Pradesh","Kerala","Goa","Banglore","Pune","Mumbai");
				//passenger.savePassenger(passenger);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		Passenger passenger = passengerService.getPassenger(id);
		
		model.addAttribute("passenger", passenger);
		return "update_passenger";
	}
	
	@GetMapping("/deletePassenger/{id}")
	public String deletePassenger(@PathVariable (value = "id") long id) {
		
		this.passengerService.deleteById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Passenger> page = passengerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Passenger> listPassenger = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPassenger", listPassenger);
		return "index";
	}
}
