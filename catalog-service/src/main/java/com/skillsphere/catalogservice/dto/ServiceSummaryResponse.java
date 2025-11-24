package com.skillsphere.catalogservice.dto;

public record ServiceSummaryResponse(
        Long id, String title, String category, Double basePrice
) {}
