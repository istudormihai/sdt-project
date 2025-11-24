package com.skillsphere.orderservice.dto;

public record PaymentDetails(String cardNumber, String cardHolderName,
                             String expiry, String cvv, String billingAddress) {}
