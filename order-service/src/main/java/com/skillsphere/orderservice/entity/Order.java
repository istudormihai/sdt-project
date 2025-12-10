package com.skillsphere.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buyerId;
    private Long sellerId;
    private Long serviceId;

    private String tier; // BASIC / SILVER / GOLD
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String paymentTransactionId;
}