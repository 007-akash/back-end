package com.wipro.bookingms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.bookingms.model.Location;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private FlightClient locationClient;

    @InjectMocks
    private LocationController locationController;
    
    private List<Location> expectedLocations;

    @BeforeEach
    void setUp() {
        expectedLocations = List.of(new Location(), new Location());
    }

    @Test
    void testGetLocations() {
        when(locationClient.getAllLocations()).thenReturn(expectedLocations);

        List<Location> actualLocations = locationController.getLocations();

        assertEquals(expectedLocations, actualLocations);
    }
}
