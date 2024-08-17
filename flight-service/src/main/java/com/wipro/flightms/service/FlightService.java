package com.wipro.flightms.service;

import java.time.LocalDate;
import java.util.List;

import com.wipro.flightms.entity.Flights;
import com.wipro.flightms.model.FlightFilterRequest;

public interface FlightService {

	List<Flights> getFlights(Long source, Long destination, LocalDate date);

	Flights getFlight(Long id);

	List<Flights> filterFlights(FlightFilterRequest request);

}
