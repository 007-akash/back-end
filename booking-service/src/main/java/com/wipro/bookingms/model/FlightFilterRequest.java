package com.wipro.bookingms.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FlightFilterRequest {
	private String stopOver;
	private String flightName;
	private boolean morningDeparture;
	private boolean eveningDeparture;
	private BigDecimal startPrice;
	private BigDecimal maxPrice;
	private LocalDate travelDate;
	private Long sourceId;
	private Long destinationId;
	
	public String getStopOver() {
		return stopOver;
	}
	public void setStopOver(String stopOver) {
		this.stopOver = stopOver;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public boolean isMorningDeparture() {
		return morningDeparture;
	}
	public void setMorningDeparture(boolean morningDeparture) {
		this.morningDeparture = morningDeparture;
	}
	public boolean isEveningDeparture() {
		return eveningDeparture;
	}
	public void setEveningDeparture(boolean eveningDeparture) {
		this.eveningDeparture = eveningDeparture;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Long getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}
	
	

}
