package com.wipro.paymentms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.paymentms.entity.Payment;
import com.wipro.paymentms.repository.PaymentRepository;
import com.wipro.paymentms.service.PaymentService;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	@Transactional
	public Payment processPayment(Payment payment) {
		payment.setStatus("INITIATED");
		return paymentRepository.save(payment);
	}

	@Override
	@Transactional
	public Payment updatePaymentStatus(Long paymentId, String status) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment not found"));
		payment.setStatus(status);
		return paymentRepository.save(payment);
	}

}
