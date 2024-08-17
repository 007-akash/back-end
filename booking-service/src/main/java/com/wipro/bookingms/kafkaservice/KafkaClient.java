package com.wipro.bookingms.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wipro.bookingms.model.Payment;
import com.wipro.bookingms.service.BookingService;

@Service
public class KafkaClient {

	@Autowired
	private BookingService bookingService;

	@KafkaListener(topics = "payment-topic", groupId = "booking-group")
	public void listenPaymentStatus(Payment payment) {
		System.out.println("Message received from Payment");
		bookingService.updateBookingStatus(payment.getBookingId(), payment.getStatus());
	}

}
