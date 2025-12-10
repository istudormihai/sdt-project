package com.skillsphere.paymentservice.entity;

public interface PaymentGateway {
    PaymentResult processPayment(Long orderId, double amount, PaymentDetails details);
    PaymentResult refundPayment(String transactionId);
}