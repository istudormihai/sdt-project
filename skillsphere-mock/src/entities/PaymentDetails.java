package entities;

public class PaymentDetails {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private String billingAddress;

    public PaymentDetails(String cardNumber, String cardHolderName,
                          String expiryDate, String cvv, String billingAddress) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.billingAddress = billingAddress;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    public String getCardNumber() { return cardNumber; }
    public String getCardHolderName() { return cardHolderName; }
    public String getExpiryDate() { return expiryDate; }
    public String getCvv() { return cvv; }
    public String getBillingAddress() { return billingAddress; }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }
}