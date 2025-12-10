package com.skillsphere.catalogservice.dto;

public record CreateServiceRequest(
        Long sellerId,
        String title,
        String category,
        String description,
        Double basePrice,
        Integer baseDeliveryDays
) {}
