package com.skillsphere.orderservice.dto;

public record PaymentResponse(String transactionId, boolean success, String message) {}
