package com.wipro.paymentms.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wipro.paymentms.entity.Payment;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Payment> kafkaTemplate;

	public void sendPaymentEvent(Payment payment) {
        kafkaTemplate.send("payment-topic", payment);
    }

}
