package com.skillsphere.paymentservice.controller;

import com.skillsphere.paymentservice.dto.PaymentRequest;
import com.skillsphere.paymentservice.dto.PaymentResponse;
import com.skillsphere.paymentservice.entity.PaymentGatewayProxy;
import com.skillsphere.paymentservice.entity.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentGatewayProxy proxy;

    @PostMapping
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        PaymentResult result = proxy.processPayment(
                request.orderId(), request.amount(), request.details());
        return new PaymentResponse(result.transactionId(), result.success(), result.message());
    }

    @PostMapping("/refund/{txId}")
    public PaymentResponse refund(@PathVariable String txId) {
        PaymentResult result = proxy.refundPayment(txId);
        return new PaymentResponse(result.transactionId(), result.success(), result.message());
    }
}
