package com.mobitech.payment.mappers;

import com.mobitech.payment.dto.PaymentDto;
import com.mobitech.payment.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {
public PaymentDto toDto(Payment payment){
    if (payment == null) return null;

    return PaymentDto.builder()
            .id(payment.getId())
            .customerId(payment.getCustomerId())
            .amount(payment.getAmount())
            .paymentType(payment.getPaymentType())
            .status(payment.getStatus())
            .transactionReference(payment.getTransactionReference())
            .build();
}

public Payment toEntity(PaymentDto paymentDto){
    if(paymentDto == null) return null;
    return Payment.builder()
            .id(paymentDto.getId())
            .customerId(paymentDto.getCustomerId())
            .amount(paymentDto.getAmount())
            .paymentType(paymentDto.getPaymentType())
            .status(paymentDto.getStatus())
            .transactionReference(paymentDto.getTransactionReference())
            .build();
}
    public List<PaymentDto> entityListToDtoList(List<Payment> customers) {
        if (customers == null) return Collections.emptyList();
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
