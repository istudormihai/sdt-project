package com.skillsphere.paymentservice.entity;

public record PaymentResult(String transactionId, boolean success, String message, double amount) {}