package com.wipro.flightms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.flightms.entity.Location;
import com.wipro.flightms.service.LocationService;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {
	
	@Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;
    
    @Test
    void testGetAllLocations() {
        List<Location> expectedLocations = List.of(new Location(), new Location());
        when(locationService.getAllLocations()).thenReturn(expectedLocations);

        List<Location> actualLocations = locationController.getAllLocations();

        assertEquals(expectedLocations, actualLocations);
    }

    @Test
    void testGetAllLocationsEmptyList() {
        when(locationService.getAllLocations()).thenReturn(List.of());

        List<Location> actualLocations = locationController.getAllLocations();

        assertEquals(0, actualLocations.size());
    }

    @Test
    void testGetAllLocationsNull() {
        when(locationService.getAllLocations()).thenReturn(null);

        List<Location> actualLocations = locationController.getAllLocations();

        assertEquals(null, actualLocations);
    }

}
