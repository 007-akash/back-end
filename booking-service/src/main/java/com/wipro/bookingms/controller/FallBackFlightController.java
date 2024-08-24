package com.wipro.bookingms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wipro.bookingms.model.FlightFilterRequest;
import com.wipro.bookingms.model.Flights;
import com.wipro.bookingms.model.Location;

@Component
public class FallBackFlightController implements FlightClient {

	@Override
	public List<Flights> getFlights(Long source, Long destination, LocalDate date) {
		return null;
	}

	@Override
	public Flights getFlightById(Long id) {
		return null;
	}

	@Override
	public List<Flights> getFlightByFilter(FlightFilterRequest request) {
		return null;
	}

	@Override
	public List<Location> getAllLocations() {
		return null;
	}
	
	

}
