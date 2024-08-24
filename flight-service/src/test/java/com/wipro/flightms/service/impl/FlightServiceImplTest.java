package com.wipro.flightms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.flightms.entity.Flights;
import com.wipro.flightms.exception.ResourceNotFoundException;
import com.wipro.flightms.exception.ValidationException;
import com.wipro.flightms.model.FlightFilterRequest;
import com.wipro.flightms.repository.FlightRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

	@Mock
    private FlightRepository flightRepository;

    @Mock
    @PersistenceContext
    private EntityManager entityManager;

    @InjectMocks
    private FlightServiceImpl flightService;
    
    @Test
    void testGetFlightsValid() {
        Long source = 1L;
        Long destination = 2L;
        LocalDate date = LocalDate.now();

        List<Flights> expectedFlights = List.of(new Flights());
        when(flightRepository.findBySourceIdAndDestinationIdAndTravelDate(source, destination, date)).thenReturn(expectedFlights);

        List<Flights> actualFlights = flightService.getFlights(source, destination, date);

        assertEquals(expectedFlights, actualFlights);
    }
    
    @Test
    void testGetFlightsNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            flightService.getFlights(null, 2L, LocalDate.now());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            flightService.getFlights(1L, null, LocalDate.now());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            flightService.getFlights(1L, 2L, null);
        });
    }
    
    @Test
    void testGetFlightByIdValid() {
        Long id = 1L;
        Flights expectedFlight = new Flights();
        when(flightRepository.findById(id)).thenReturn(Optional.of(expectedFlight));

        Flights actualFlight = flightService.getFlight(id);

        assertEquals(expectedFlight, actualFlight);
    }
    
    @Test
    void testGetFlightByIdNotFound() {
        Long id = 1L;
        when(flightRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            flightService.getFlight(id);
        });
    }

    @Test
    void testFilterFlightsWithNullRequest() {
        assertThrows(ValidationException.class, () -> {
            flightService.filterFlights(null);
        });
    }

    @Test
    void testFilterFlightsWithInvalidRequest() {
        FlightFilterRequest request = new FlightFilterRequest();
        request.setSourceId(1L);
        request.setDestinationId(2L);
        request.setTravelDate(null);  // Invalid since travel date is required

        assertThrows(ValidationException.class, () -> {
            flightService.filterFlights(request);
        });
    }

    @Test
    void testFilterFlightsWithMorningAndEveningDeparture() {
        FlightFilterRequest request = new FlightFilterRequest();
        request.setSourceId(1L);
        request.setDestinationId(2L);
        request.setTravelDate(LocalDate.now());
        request.setMorningDeparture(true);
        request.setEveningDeparture(true);  // Invalid combination of Morning and Departure as true

        assertThrows(ValidationException.class, () -> {
            flightService.filterFlights(request);
        });
    }
}
