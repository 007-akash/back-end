package com.wipro.flightms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.flightms.entity.Flights;
import com.wipro.flightms.model.FlightFilterRequest;
import com.wipro.flightms.service.FlightService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
	
	private final FlightService flightService;
	
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}
	
	@GetMapping
	@CircuitBreaker(name = "flightService", fallbackMethod = "flightServiceFallback")
    public List<Flights> getFlights(@RequestParam Long source, @RequestParam Long destination, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.getFlights(source, destination, date);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "flightService", fallbackMethod = "flightServiceFallback")
    public Flights getFlightById(@PathVariable Long id) {
        return flightService.getFlight(id);
    }

    @PostMapping("/filter")
    public List<Flights> filterFlights(@RequestBody FlightFilterRequest request) {
        return flightService.filterFlights(request);
    }
    
    public List<Flights> flightServiceFallback(Exception e) {
        return List.of();
    }
}
