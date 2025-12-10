package com.skillsphere.orderservice.controller;

import com.skillsphere.orderservice.dto.CreateOrderRequest;
import com.skillsphere.orderservice.dto.OrderResponse;
import com.skillsphere.orderservice.dto.PaymentDetails;
import com.skillsphere.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(req));
    }

    @PostMapping("/{id}/pay")
    public OrderResponse pay(@PathVariable Long id, @RequestBody PaymentDetails details) {
        return service.pay(id, details);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return service.getOrder(id);
    }
}