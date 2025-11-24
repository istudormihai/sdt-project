package com.skillsphere.orderservice.dto;

public record PaymentRequest(Long orderId, double amount, PaymentDetails details) {}
