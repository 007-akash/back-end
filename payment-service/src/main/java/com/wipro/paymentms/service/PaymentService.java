package com.wipro.paymentms.service;

import com.wipro.paymentms.entity.Payment;

public interface PaymentService {

	Payment processPayment(Payment payment);
    Payment updatePaymentStatus(Long paymentId, String status);
}
