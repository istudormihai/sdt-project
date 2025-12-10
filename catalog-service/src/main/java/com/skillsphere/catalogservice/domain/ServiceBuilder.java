package com.skillsphere.catalogservice.domain;

public class ServiceBuilder {

    private Long sellerId;
    private String title;
    private String category;
    private String description;
    private Double basePrice;
    private Integer baseDeliveryTimeDays;

    public ServiceBuilder seller(Long sellerId) { this.sellerId = sellerId; return this; }
    public ServiceBuilder title(String title) { this.title = title; return this; }
    public ServiceBuilder category(String category) { this.category = category; return this; }
    public ServiceBuilder description(String description) { this.description = description; return this; }
    public ServiceBuilder basePrice(Double price) { this.basePrice = price; return this; }
    public ServiceBuilder deliveryTime(int days) { this.baseDeliveryTimeDays = days; return this; }

    public ServiceEntity build() {
        if (title == null || category == null || basePrice == null)
            throw new IllegalStateException("Missing mandatory fields");
        return ServiceEntity.builder()
                .sellerId(sellerId)
                .title(title)
                .category(category)
                .description(description)
                .basePrice(basePrice)
                .baseDeliveryTimeDays(baseDeliveryTimeDays)
                .build();
    }
}
