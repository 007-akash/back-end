package com.wipro.bookingms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.bookingms.model.FlightFilterRequest;
import com.wipro.bookingms.model.Flights;
import com.wipro.bookingms.model.Location;


@FeignClient(name="flight-service", fallback = FallBackFlightController.class)
public interface FlightClient {
	
	@GetMapping("/api/flights")
    public List<Flights> getFlights(@RequestParam Long source, @RequestParam Long destination, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @GetMapping("/api/flights/{id}")
    public Flights getFlightById(@PathVariable Long id);
    
    @PostMapping("/api/flights/filter")
    public List<Flights> getFlightByFilter(@RequestBody FlightFilterRequest request);
    
    @GetMapping("/locations")
	List<Location> getAllLocations();

}
