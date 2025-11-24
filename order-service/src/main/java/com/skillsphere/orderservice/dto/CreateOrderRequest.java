package com.skillsphere.orderservice.dto;

public record CreateOrderRequest(Long buyerId, Long serviceId, String tier) {}

