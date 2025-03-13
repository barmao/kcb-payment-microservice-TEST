package com.mobitech.payment.controller;

import com.mobitech.payment.config.Auth0TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dev")
public class DevAuthController {

    private final Auth0TokenProvider tokenProvider;
    private static final Logger logger = LoggerFactory.getLogger(DevAuthController.class);

    public DevAuthController(Auth0TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken() {
        try {
            logger.info("Token request received");
            String token = tokenProvider.getAccessToken();
            logger.info("Token successfully retrieved");
            return ResponseEntity.ok(Map.of("access_token", token));
        } catch (Exception e) {
            logger.error("Error retrieving token: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
