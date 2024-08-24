package com.wipro.bookingms.service;

import com.wipro.bookingms.entity.Booking;
import com.wipro.bookingms.model.BookingResponse;

public interface BookingService {

	BookingResponse createBooking(Booking booking);
    BookingResponse updateBookingStatus(Long bookingId, String status);
	BookingResponse getBooking(Long bookingId);
	BookingResponse getBookingStatus(Long bookingId);
}
