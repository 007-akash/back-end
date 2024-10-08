package com.wipro.bookingms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bookingms.model.FlightFilterRequest;
import com.wipro.bookingms.model.Flights;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	@Autowired
	FlightClient flightClient;
	
	@GetMapping
	public List<Flights> getFlights(@RequestParam Long source, @RequestParam Long destination, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightClient.getFlights(source, destination, date);
    }
	@PostMapping("/filter")
    public List<Flights> filterFlights(@RequestBody FlightFilterRequest request) {
        return flightClient.getFlightByFilter(request);
    }
}
