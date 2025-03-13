package com.mobitech.payment.controller;

import com.mobitech.payment.dto.ApiResponseDto;
import com.mobitech.payment.entity.Payment;
import com.mobitech.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> processPayment(@RequestBody Payment payment) {
        logger.info("Processing payment for customer ID: {}", payment.getCustomerId());

        try {
            String transactionId = paymentService.processPayment(payment);
            return ResponseEntity.ok(ApiResponseDto.success("Payment processed successfully", transactionId));
        } catch (Exception e) {
            logger.error("Error processing payment: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponseDto.error("Payment failed", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getPayment(@PathVariable Long id) {
        logger.info("Fetching payment details for ID: {}", id);

        try {
            Payment payment = paymentService.getPaymentById(id);
            return ResponseEntity.ok(ApiResponseDto.success("Payment retrieved successfully", payment));
        } catch (Exception e) {
            logger.error("Payment not found: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponseDto.error("Payment not found", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching payments - Page: {}, Size: {}", page, size);

        List<Payment> payments = paymentService.getAllPayments(page, size);
        return ResponseEntity.ok(ApiResponseDto.success("Payments retrieved successfully", payments));
    }
}
