package com.wipro.bookingms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.bookingms.entity.Booking;
import com.wipro.bookingms.exception.ResourceNotFoundException;
import com.wipro.bookingms.kafkaservice.KafkaProducer;
import com.wipro.bookingms.model.BookingResponse;
import com.wipro.bookingms.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

	@Mock
    private BookingRepository bookingRepository;
    
	@Mock
	private KafkaProducer kafkaProducer;
    
	@InjectMocks
	private BookingServiceImpl bookingService;


    @Test
    void testCreateBooking_Success() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setStatus("INITIATED");

        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingResponse response = bookingService.createBooking(booking);

        assertTrue(response.isStatus());
        assertEquals("Booking Initiated", response.getMessage());
        assertEquals("1", response.getBookingId());
        verify(kafkaProducer).sendBookingEvent(booking);
    }

    @Test
    void testCreateBooking_Failure() {
        Booking booking = new Booking();

        when(bookingRepository.save(booking)).thenReturn(null);

        BookingResponse response = bookingService.createBooking(booking);

        assertFalse(response.isStatus());
        assertEquals("Booking Initiation Failed", response.getMessage());
    }

    @Test
    void testUpdateBookingStatus_Success() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setStatus("INITIATED");

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingResponse response = bookingService.updateBookingStatus(bookingId, "COMPLETED");

        assertTrue(response.isStatus());
        assertEquals("1", response.getBookingId());
    }

    @Test
    void testUpdateBookingStatus_Failure() {
        Long bookingId = 1L;

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookingService.updateBookingStatus(bookingId, "COMPLETED"));
    }
}
