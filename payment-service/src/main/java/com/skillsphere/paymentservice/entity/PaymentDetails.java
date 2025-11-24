package com.skillsphere.paymentservice.entity;

public record PaymentDetails(String cardNumber, String cardHolderName, String expiry, String cvv, String billingAddress) {}
