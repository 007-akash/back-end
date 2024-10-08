package com.wipro.paymentms.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wipro.paymentms.entity.Payment;
import com.wipro.paymentms.model.Booking;
import com.wipro.paymentms.service.PaymentService;

@Service
public class KafkaClient {

	@Autowired
    private PaymentService paymentService;
	@Autowired
	KafkaProducer kafkaProducer;
	
	@KafkaListener(topics = "booking-topic", groupId = "payment-group")
    public void listenPaymentStatus(Booking booking) {
		System.out.println(booking.getBookingId() + ", " + booking.getFlightId() + ", " + booking.getPaymentMode() + ", " + booking.getAmount() + booking.getStatus());
		Payment payment = new Payment();
		payment.setBookingId(booking.getBookingId());
		payment.setPaymentMode(booking.getPaymentMode());
		payment.setAmount(booking.getAmount());
		paymentService.processPayment(payment);
        paymentService.updatePaymentStatus(payment.getId(), "COMPLETED");
        sendPaymentStatus(payment);
    }
	
	private void sendPaymentStatus(Payment payment) {
        payment.setStatus("COMPLETED");
        kafkaProducer.sendPaymentEvent(payment);
    }
}
