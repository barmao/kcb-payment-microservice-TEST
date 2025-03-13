package com.mobitech.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto {
    private boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ApiResponseDto success(String message) {
        return ApiResponseDto.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static ApiResponseDto success(String message, Object data) {
        return ApiResponseDto.builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponseDto error(String message) {
        return ApiResponseDto.builder()
                .success(false)
                .message(message)
                .build();
    }

    public static ApiResponseDto error(String message, Object data) {
        return ApiResponseDto.builder()
                .success(false)
                .message(message)
                .data(data)
                .build();
    }
}
