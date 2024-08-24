package com.wipro.flightms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.wipro.flightms.entity.Flights;
import com.wipro.flightms.model.FlightFilterRequest;
import com.wipro.flightms.service.FlightService;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {
	@Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;
    
    @Test
    void testGetFlightsValidInputs() {
        Long source = 1L;
        Long destination = 2L;
        LocalDate date = LocalDate.now();

        List<Flights> expectedFlights = List.of(new Flights());
        when(flightService.getFlights(source, destination, date)).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightController.getFlights(source, destination, date);
        assertEquals(expectedFlights, actualFlights);
    }
    
   
    @Test
    void testGetFlightsEmptyResult() {
        Long source = 1L;
        Long destination = 2L;
        LocalDate date = LocalDate.now();

        when(flightService.getFlights(source, destination, date)).thenReturn(List.of());

        List<Flights> actualFlights = flightController.getFlights(source, destination, date);
        assertTrue(actualFlights.isEmpty());
    }

    @Test
    void testGetFlightByIdValidId() {
        Long id = 1L;
        Flights expectedFlight = new Flights();
        when(flightService.getFlight(id)).thenReturn(expectedFlight);

        Flights actualFlight = flightController.getFlightById(id);
        assertEquals(expectedFlight, actualFlight);
    }
    @Test
    void testGetFlightByIdInvalidId() {
        Long id = -1L;

        when(flightService.getFlight(id)).thenReturn(null);

        Flights actualFlight = flightController.getFlightById(id);
        assertNull(actualFlight);
    }

    @Test
    void testFilterFlightsValidRequest() {
        FlightFilterRequest request = new FlightFilterRequest();
        List<Flights> expectedFlights = List.of(new Flights());

        when(flightService.filterFlights(request)).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightController.filterFlights(request);
        assertEquals(expectedFlights, actualFlights);
    }



}
