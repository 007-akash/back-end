package com.wipro.bookingms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bookingms.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
