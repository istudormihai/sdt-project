package com.skillsphere.paymentservice.entity;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class RealPaymentGateway implements PaymentGateway {

    public PaymentResult processPayment(Long orderId, double amount, PaymentDetails details) {
        String txId = UUID.randomUUID().toString();
        return new PaymentResult(txId, true, "Payment processed by external gateway", amount);
    }

    public PaymentResult refundPayment(String transactionId) {
        return new PaymentResult(transactionId, true, "Refund processed", 0);
    }
}
