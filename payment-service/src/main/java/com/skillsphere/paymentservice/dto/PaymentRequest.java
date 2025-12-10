package com.skillsphere.paymentservice.dto;

import com.skillsphere.paymentservice.entity.PaymentDetails;

public record PaymentRequest(Long orderId, double amount, PaymentDetails details) {}

