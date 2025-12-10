package com.skillsphere.orderservice.service;

import com.skillsphere.orderservice.dto.*;
import com.skillsphere.orderservice.entity.Order;
import com.skillsphere.orderservice.entity.OrderStatus;
import com.skillsphere.orderservice.messaging.OrderEventPublisher;
import com.skillsphere.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;
    private final RestTemplate restTemplate;
    private final OrderEventPublisher eventPublisher;


    @Value("${services.catalog.url}")
    private String catalogBaseUrl;

    @Value("${services.payment.url}")
    private String paymentBaseUrl;

    public OrderResponse getOrder(Long id) {
        Order order = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return toResponse(order);
    }

    public OrderResponse createOrder(CreateOrderRequest req) {
        TierQuoteResponse quote = restTemplate.getForObject(
                catalogBaseUrl + "/api/catalog/services/{id}/tier/{tier}",
                TierQuoteResponse.class,
                req.serviceId(), req.tier());

        Order order = Order.builder()
                .buyerId(req.buyerId())
                .serviceId(req.serviceId())
                .sellerId(0L)
                .tier(req.tier().toUpperCase())
                .price(quote.price())
                .status(OrderStatus.PENDING)
                .build();
        Order saved = repo.save(order);
        eventPublisher.publishOrderEvent(saved);
        return toResponse(saved);
    }

    public OrderResponse pay(Long orderId, PaymentDetails details) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Order not in PENDING state");
        }
        PaymentRequest req = new PaymentRequest(order.getId(), order.getPrice(), details);
        PaymentResponse resp = restTemplate.postForObject(
                paymentBaseUrl + "/api/payments", req, PaymentResponse.class);
        if (resp != null && resp.success()) {
            order.setStatus(OrderStatus.PAID);
            order.setPaymentTransactionId(resp.transactionId());
            repo.save(order);
        }
        eventPublisher.publishOrderEvent(order);
        return toResponse(order);
    }

    private OrderResponse toResponse(Order o) {
        return new OrderResponse(o.getId(), o.getBuyerId(), o.getServiceId(),
                o.getTier(), o.getPrice(), o.getStatus());
    }
}

