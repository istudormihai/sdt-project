package com.skillsphere.paymentservice.dto;

public record PaymentResponse(String transactionId, boolean success, String message) {}

