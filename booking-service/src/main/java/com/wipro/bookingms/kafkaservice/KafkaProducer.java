package com.wipro.bookingms.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wipro.bookingms.entity.Booking;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Booking> kafkaTemplate;

	public void sendBookingEvent(Booking booking) {
		kafkaTemplate.send("booking-topic", booking);
	}

}
