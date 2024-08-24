package com.wipro.flightms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.flightms.entity.Location;
import com.wipro.flightms.repository.LocationRepository;
import com.wipro.flightms.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
    private LocationRepository locationRepository;

	@Override
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

}
