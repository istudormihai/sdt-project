package com.skillsphere.orderservice.dto;

import com.skillsphere.orderservice.entity.OrderStatus;

public record OrderResponse(Long id, Long buyerId, Long serviceId,
                            String tier, Double price, OrderStatus status) {}