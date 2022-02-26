package com.example.BusTicketReservationSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.BusTicketReservationSystem.dto.UserRegistrationDto;
import com.example.BusTicketReservationSystem.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
