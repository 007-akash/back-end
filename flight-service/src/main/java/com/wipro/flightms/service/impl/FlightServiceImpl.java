package com.wipro.flightms.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wipro.flightms.entity.Flights;
import com.wipro.flightms.exception.ResourceNotFoundException;
import com.wipro.flightms.exception.ValidationException;
import com.wipro.flightms.model.FlightFilterRequest;
import com.wipro.flightms.repository.FlightRepository;
import com.wipro.flightms.service.FlightService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class FlightServiceImpl implements FlightService{
	
	private final FlightRepository flightRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public FlightServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flights> getFlights(Long source, Long destination, LocalDate date) {
		if (source == null || destination == null || date == null) {
	        throw new IllegalArgumentException("Source, destination, and date must not be null");
	    }
		return flightRepository.findBySourceIdAndDestinationIdAndTravelDate(source, destination, date);
	}

	@Override
	public Flights getFlight(Long id) {
		return flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
	}

	@Override
	public List<Flights> filterFlights(FlightFilterRequest flightFilterRequest) {
	    validateFilter(flightFilterRequest);

	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Flights> cq = cb.createQuery(Flights.class);
	    Root<Flights> flight = cq.from(Flights.class);

	    List<Predicate> predicates = new ArrayList<>();

	    // Predicates conditionally based on non-null values
	    if (flightFilterRequest.getStopOver() != null) {
	        predicates.add(cb.equal(flight.get("stopOver"), flightFilterRequest.getStopOver()));
	    }

	    if (!StringUtils.isEmpty(flightFilterRequest.getFlightName())) {
	        predicates.add(cb.equal(flight.get("aircraftName"), flightFilterRequest.getFlightName()));
	    }

	    if (flightFilterRequest.getStartPrice() != null || flightFilterRequest.getMaxPrice() != null) {
	        BigDecimal minPrice = flightFilterRequest.getStartPrice() != null ? flightFilterRequest.getStartPrice() : BigDecimal.ZERO;
	        BigDecimal maxPrice = flightFilterRequest.getMaxPrice() != null ? flightFilterRequest.getMaxPrice() : BigDecimal.valueOf(Double.MAX_VALUE);
	        predicates.add(cb.between(flight.get("price"), minPrice, maxPrice));
	    }

	    if (flightFilterRequest.isMorningDeparture() || flightFilterRequest.isEveningDeparture()) {
	        LocalTime morningStartTime = flightFilterRequest.isMorningDeparture() ? LocalTime.of(5, 0) : LocalTime.of(0, 5);
	        LocalTime eveningEndTime = flightFilterRequest.isEveningDeparture() ? LocalTime.of(22, 0) : LocalTime.of(23, 55);

	        if (flightFilterRequest.isMorningDeparture()) {
	            eveningEndTime = LocalTime.of(14, 0);
	        }
	        if (flightFilterRequest.isEveningDeparture()) {
	            morningStartTime = LocalTime.of(14, 0);
	        }
	        predicates.add(cb.between(flight.get("departureTime"), morningStartTime, eveningEndTime));
	    }

	   
	    // Mandatory Fields
	    predicates.add(cb.equal(flight.get("travelDate"), flightFilterRequest.getTravelDate()));
	    predicates.add(cb.equal(flight.get("source").get("id"), flightFilterRequest.getSourceId()));
	    predicates.add(cb.equal(flight.get("destination").get("id"), flightFilterRequest.getDestinationId()));
	    

	    // Adding predicates to the query
	    cq.where(predicates.toArray(new Predicate[0]));

	    return entityManager.createQuery(cq).getResultList();
	}

	private void validateFilter(FlightFilterRequest flightFliterRequest) {
		if(ObjectUtils.isEmpty(flightFliterRequest)) {
			throw new ValidationException("Null Request Body");
		}
		boolean isMorningDeparture = flightFliterRequest.isMorningDeparture();
		boolean isEveningDeparture = flightFliterRequest.isEveningDeparture();
		if (isMorningDeparture && isEveningDeparture)
			throw new ValidationException("Bad Filter Request");
		if (flightFliterRequest.getTravelDate() == null)
			throw new ValidationException("Travel Date is Required");
		if (flightFliterRequest.getSourceId() == null) {
			throw new ValidationException("Source location ID is required.");
		}
		if (flightFliterRequest.getDestinationId() == null) {
			throw new ValidationException("Destination location ID is required.");
		}
	}

}
