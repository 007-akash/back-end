package com.wipro.flightms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.flightms.entity.Location;
import com.wipro.flightms.repository.LocationRepository;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
	@Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}
