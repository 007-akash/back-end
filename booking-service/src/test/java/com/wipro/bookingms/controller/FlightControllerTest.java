package com.wipro.bookingms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.bookingms.model.FlightFilterRequest;
import com.wipro.bookingms.model.Flights;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @Mock
    private FlightClient flightClient;

    @InjectMocks
    private FlightController flightController;

    private Long source;
    private Long destination;
    private LocalDate date;
    private FlightFilterRequest request;

    @BeforeEach
    void setUp() {
        source = 1L;
        destination = 2L;
        date = LocalDate.now();

        request = new FlightFilterRequest();
        request.setSourceId(source);
        request.setDestinationId(destination);
        request.setTravelDate(date);
    }

    @Test
    void testGetFlights() {
        List<Flights> expectedFlights = List.of(new Flights());
        when(flightClient.getFlights(source, destination, date)).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightController.getFlights(source, destination, date);

        assertEquals(expectedFlights, actualFlights);
    }

    @Test
    void testFilterFlights() {
        List<Flights> expectedFlights = List.of(new Flights());
        when(flightClient.getFlightByFilter(request)).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightController.filterFlights(request);

        assertEquals(expectedFlights, actualFlights);
    }
    
    @AfterEach
    void destroy() {
    	request = null;
    }
}
