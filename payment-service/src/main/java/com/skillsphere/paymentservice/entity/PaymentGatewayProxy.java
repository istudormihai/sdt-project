package com.skillsphere.paymentservice.entity;

import com.skillsphere.paymentservice.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayProxy implements PaymentGateway {

    private final RealPaymentGateway real;
    private final PaymentTransactionRepository repo;

    @Override
    public PaymentResult processPayment(Long orderId, double amount, PaymentDetails details) {
        // validation + logging + duplicate check (simplified)
        if (amount <= 0) {
            return new PaymentResult(null, false, "Invalid amount", amount);
        }
        PaymentResult result = real.processPayment(orderId, amount, details);
        repo.save(PaymentTransaction.builder()
                .orderId(orderId)
                .transactionId(result.transactionId())
                .amount(result.amount())
                .status(result.success() ? TransactionStatus.COMPLETED : TransactionStatus.FAILED)
                .message(result.message())
                .build());
        return result;
    }

    @Override
    public PaymentResult refundPayment(String transactionId) {
        PaymentResult res = real.refundPayment(transactionId);
        repo.findByTransactionId(transactionId).ifPresent(tx -> {
            tx.setStatus(TransactionStatus.REFUNDED);
            repo.save(tx);
        });
        return res;
    }
}