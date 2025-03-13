package com.mobitech.payment.service;

import com.mobitech.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    String processPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments(int page, int size);

}
