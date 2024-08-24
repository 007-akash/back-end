package com.wipro.paymentms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.paymentms.entity.Payment;
import com.wipro.paymentms.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

	@Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
	private PaymentServiceImpl paymentService;


    @Test
    void testProcessPayment_Success() {
        Payment payment = new Payment();
        payment.setStatus("INITIATED");

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.processPayment(payment);

        assertEquals("INITIATED", result.getStatus());
        verify(paymentRepository).save(payment);
    }

    @Test
    void testUpdatePaymentStatus_Success() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setStatus("INITIATED");

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.updatePaymentStatus(paymentId, "COMPLETED");

        assertEquals("COMPLETED", result.getStatus());
        verify(paymentRepository).save(payment);
    }

    @Test
    void testUpdatePaymentStatus_PaymentNotFound() {
        Long paymentId = 1L;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> paymentService.updatePaymentStatus(paymentId, "COMPLETED"));
    }
}
