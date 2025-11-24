package com.skillsphere.catalogservice.dto;

import java.util.List;

public record TierQuoteResponse(
        Long serviceId,
        String tier,
        double price,
        int deliveryDays,
        List<String> features
) {}