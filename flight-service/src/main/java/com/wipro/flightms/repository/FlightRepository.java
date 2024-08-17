package com.wipro.flightms.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.flightms.entity.Flights;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long>{

	List<Flights> findBySourceIdAndDestinationIdAndTravelDate(Long sourceId, Long destinationId, LocalDate travelDate);

	List<Flights> findByStopOverAndAircraftNameAndPriceBetweenAndDepartureTimeBetweenAndTravelDate(String stopOver,
			String flightName, BigDecimal startPrice, BigDecimal maxPrice, LocalTime morningStartTime, LocalTime eveningEndTime, 
			LocalDate travelDate);

}
