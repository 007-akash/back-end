package com.wipro.flightms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.flightms.entity.Location;
import com.wipro.flightms.repository.LocationRepository;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {
	
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void testGetAllLocations() {
        List<Location> expectedLocations = List.of(new Location(), new Location());
        when(locationRepository.findAll()).thenReturn(expectedLocations);

        List<Location> actualLocations = locationService.getAllLocations();

        assertEquals(expectedLocations, actualLocations);
    }

    @Test
    void testGetAllLocationsEmptyList() {
        when(locationRepository.findAll()).thenReturn(List.of());

        List<Location> actualLocations = locationService.getAllLocations();

        assertEquals(0, actualLocations.size());
    }

    @Test
    void testGetAllLocationsNull() {
        when(locationRepository.findAll()).thenReturn(null);

        List<Location> actualLocations = locationService.getAllLocations();

        assertEquals(null, actualLocations);
    }

}
