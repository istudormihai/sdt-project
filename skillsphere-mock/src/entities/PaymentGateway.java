package entities;

public interface PaymentGateway {
    PaymentResult processPayment(Order order, PaymentDetails paymentDetails);
    PaymentResult refundPayment(String transactionId);
    TransactionStatus getTransactionStatus(String transactionId);
}