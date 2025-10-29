package entities;

import java.util.Date;

public class PaymentResult {
    private String transactionId;
    private boolean success;
    private String message;
    private Date timestamp;
    private double amount;

    public PaymentResult(String transactionId, boolean success, String message, double amount) {
        this.transactionId = transactionId;
        this.success = success;
        this.message = message;
        this.timestamp = new Date();
        this.amount = amount;
    }

    public String getTransactionId() { return transactionId; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Date getTimestamp() { return timestamp; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "PaymentResult{" +
                "transactionId='" + transactionId + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", amount=$" + amount +
                '}';
    }
}