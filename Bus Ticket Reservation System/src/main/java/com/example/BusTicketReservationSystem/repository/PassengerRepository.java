package com.example.BusTicketReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.BusTicketReservationSystem.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
