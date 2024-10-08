package com.wipro.bookingms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bookingms.model.Location;

@RestController
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	FlightClient locationClient;
	
	@GetMapping
	public List<Location> getLocations(){
		return locationClient.getAllLocations();
	}

}
