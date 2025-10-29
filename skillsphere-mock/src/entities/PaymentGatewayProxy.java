package entities;

import java.util.HashMap;
import java.util.Map;

public class PaymentGatewayProxy implements PaymentGateway {
    private RealPaymentGateway realGateway;
    private Map<String, String> transactionLog; // Simple logging

    public PaymentGatewayProxy() {
        this.realGateway = new RealPaymentGateway();
        this.transactionLog = new HashMap<>();
        System.out.println("PaymentGatewayProxy initialized");
    }

    @Override
    public PaymentResult processPayment(Order order, PaymentDetails paymentDetails) {
        System.out.println("\n=== PAYMENT GATEWAY PROXY ===");
        System.out.println("Proxy intercepting payment request...");

        if (!validatePaymentDetails(paymentDetails)) {
            System.out.println("❌ Payment validation failed!");
            return new PaymentResult(
                    null,
                    false,
                    "Invalid payment details",
                    order.getTotalPrice()
            );
        }

        if (checkDuplicateTransaction(order)) {
            System.out.println("❌ Duplicate transaction detected!");
            return new PaymentResult(
                    null,
                    false,
                    "Duplicate transaction",
                    order.getTotalPrice()
            );
        }

        logTransaction(order.getOrderId(), "INITIATED");

        System.out.println("✓ Validation passed. Forwarding to real gateway...");
        PaymentResult result = realGateway.processPayment(order, paymentDetails);

        if (result.isSuccess()) {
            logTransaction(order.getOrderId(), "SUCCESS: " + result.getTransactionId());
            transactionLog.put(order.getOrderId(), result.getTransactionId());
        } else {
            logTransaction(order.getOrderId(), "FAILED");
        }

        System.out.println("=== PROXY COMPLETE ===\n");
        return result;
    }

    private boolean validatePaymentDetails(PaymentDetails details) {
        System.out.println("Validating payment details...");

        if (details == null) {
            return false;
        }
        if (details.getCardHolderName() == null || details.getCardHolderName().isEmpty()) {
            System.out.println("Invalid card holder name");
            return false;
        }
        if (details.getCardNumber() == null || details.getCardNumber().isEmpty()) {
            System.out.println("Invalid card number");
            return false;
        }

        System.out.println("✓ Payment details valid");
        return true;
    }

    private boolean checkDuplicateTransaction(Order order) {
        System.out.println("Checking for duplicate transactions...");
        boolean isDuplicate = transactionLog.containsKey(order.getOrderId());

        if (isDuplicate) {
            System.out.println("Found existing transaction for order: " + order.getOrderId());
        } else {
            System.out.println("✓ No duplicate found");
        }

        return isDuplicate;
    }

    private void logTransaction(String orderId, String message) {
        String logEntry = "[" + new java.util.Date() + "] Order " + orderId + ": " + message;
        System.out.println("📝 LOG: " + logEntry);
    }

    @Override
    public PaymentResult refundPayment(String transactionId) {
        System.out.println("Proxy: Processing refund...");
        logTransaction(transactionId, "REFUND_INITIATED");
        return realGateway.refundPayment(transactionId);
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        System.out.println("Proxy: Checking transaction status...");
        return realGateway.getTransactionStatus(transactionId);
    }

    public void printTransactionLog() {
        System.out.println("\n=== TRANSACTION LOG ===");
        transactionLog.forEach((orderId, txnId) ->
                System.out.println("Order: " + orderId + " -> Transaction: " + txnId)
        );
        System.out.println("=======================\n");
    }
}