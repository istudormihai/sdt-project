package entities;

public class RealPaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult processPayment(Order order, PaymentDetails paymentDetails) {
        System.out.println("\n=== REAL PAYMENT GATEWAY ===");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Processing payment of $" + order.getTotalPrice());
        System.out.println("Card: " + paymentDetails.getCardNumber());

        String transactionId = "TXN-" + System.currentTimeMillis();

        System.out.println("Payment processed successfully!");
        System.out.println("Transaction ID: " + transactionId);

        return new PaymentResult(
                transactionId,
                true,
                "Payment processed successfully",
                order.getTotalPrice()
        );
    }

    @Override
    public PaymentResult refundPayment(String transactionId) {
        System.out.println("Processing refund for transaction: " + transactionId);
        return new PaymentResult(
                "REFUND-" + transactionId,
                true,
                "Refund processed successfully",
                0.0
        );
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        System.out.println("Fetching transaction status for: " + transactionId);
        return TransactionStatus.COMPLETED;
    }
}