package com.mobitech.payment.service;

import org.springframework.stereotype.Service;

@Service
public interface SmsService {
    /**
     * Sends an SMS notification to a recipient.
     */
    void sendSms(String phoneNumber, String message);
}
