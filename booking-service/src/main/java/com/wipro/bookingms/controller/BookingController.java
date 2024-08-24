package com.wipro.bookingms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bookingms.entity.Booking;
import com.wipro.bookingms.model.BookingResponse;
import com.wipro.bookingms.service.BookingService;

@RestController
@RequestMapping("/api/payment")
public class BookingController {
	
	private final BookingService bookingService;
	
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
    public BookingResponse createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }
	
	@GetMapping
    public BookingResponse getBooking(@RequestParam Long bookingId) {
        return bookingService.getBooking(bookingId);
    }
	
	@GetMapping("/status")
    public BookingResponse getBookingStatus(@RequestParam Long bookingId) {
        return bookingService.getBookingStatus(bookingId);
    }
	

}
