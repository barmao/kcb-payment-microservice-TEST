package com.mobitech.payment.service;

import com.mobitech.payment.entity.Payment;
import com.mobitech.payment.repository.PaymentRepository;
import com.mobitech.payment.service.PaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String processPayment(Payment payment) {
        // Save payment to database
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment.getTransactionReference();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
    }

    @Override
    public List<Payment> getAllPayments(int page, int size) {
        return paymentRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
