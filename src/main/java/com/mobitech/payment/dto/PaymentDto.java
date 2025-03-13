package com.mobitech.payment.dto;

import com.mobitech.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private Long customerId;
    private Float amount;
    private String paymentType;
    private Payment.PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String transactionReference;
}
