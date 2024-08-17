package com.wipro.paymentms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.paymentms.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
