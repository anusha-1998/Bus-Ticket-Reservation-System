package com.example.BusTicketReservationSystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.BusTicketReservationSystem.model.Passenger;

public interface PassengerService {
	List<Passenger> getAllPassenger();
	void savePassenger(Passenger passenger);
	Passenger getPassenger(long id);
	void deleteById(long id);
	Page<Passenger> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}