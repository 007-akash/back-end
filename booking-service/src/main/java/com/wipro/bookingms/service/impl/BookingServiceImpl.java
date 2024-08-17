package com.wipro.bookingms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wipro.bookingms.entity.Booking;
import com.wipro.bookingms.exception.ResourceNotFoundException;
import com.wipro.bookingms.kafkaservice.KafkaProducer;
import com.wipro.bookingms.model.BookingResponse;
import com.wipro.bookingms.repository.BookingRepository;
import com.wipro.bookingms.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService{

	private final BookingRepository bookingRepository;
	private final KafkaProducer kafkaProducer;
	
	public BookingServiceImpl(BookingRepository bookingRepository, KafkaProducer kafkaProducer) {
		this.bookingRepository = bookingRepository;
		this.kafkaProducer = kafkaProducer;
	}
	
	@Override
	@Transactional
	public BookingResponse createBooking(Booking booking) {
		BookingResponse bookingResponse = new BookingResponse();
        booking.setStatus("INITIATED");
		Booking savedBooking = bookingRepository.save(booking);
		if(!ObjectUtils.isEmpty(savedBooking)) {
			bookingResponse.setMessage("Booking Initiated");
			bookingResponse.setStatus(true);
			bookingResponse.setBookingId(String.valueOf(savedBooking.getBookingId()));
			updateBookingEvent(savedBooking);
		}
		else {
			bookingResponse.setMessage("Booking Initiation Failed");
			bookingResponse.setStatus(false);
		}
		return bookingResponse;
	}

	@Override
	@Transactional
	public BookingResponse updateBookingStatus(Long bookingId, String status) {
		BookingResponse bookingResponse = new BookingResponse();
		Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(status);
        Booking updatedBooking = bookingRepository.save(booking);
        bookingResponse.setBookingId(String.valueOf(updatedBooking.getBookingId()));
        if(!ObjectUtils.isEmpty(updatedBooking)) {
			bookingResponse.setMessage(updatedBooking.getStatus() + " status is Upated");
			bookingResponse.setStatus(true);
			
			System.out.println("Bookng Success");
		}
		else {
			bookingResponse.setMessage("Update Booking Failed");
			bookingResponse.setStatus(false);
			System.out.println("Bookng Failed");
		}
		return bookingResponse;
	}
	
	private void updateBookingEvent(Booking booking) {
		kafkaProducer.sendBookingEvent(booking);
	}

	@Override
	public BookingResponse getBooking(Long bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
