package entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceBuilder {
    private String sellerId;

    private String title;
    private String description;
    private String category;
    private double basePrice;
    private int deliveryTime;

    public ServiceBuilder(Long sellerId) {
        this.sellerId = sellerId.toString();
        this.basePrice = 0.0;
        this.deliveryTime = 7;
    }

    public ServiceBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ServiceBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ServiceBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ServiceBuilder setBasePrice(double price) {
        this.basePrice = price;
        return this;
    }

    public ServiceBuilder setDeliveryTime(int days) {
        this.deliveryTime = days;
        return this;
    }

    private boolean validate() {
        if (title == null || title.trim().isEmpty()) {
            System.err.println("Error: Service title is required!");
            return false;
        }
        if (category == null || category.trim().isEmpty()) {
            System.err.println("Error: Service category is required!");
            return false;
        }
        if (basePrice < 0) {
            System.err.println("Error: Base price cannot be negative!");
            return false;
        }
        if (deliveryTime <= 0) {
            System.err.println("Error: Delivery time must be positive!");
            return false;
        }
        return true;
    }

    public Service build() {
        System.out.println("Building service...");

        if (!validate()) {
            throw new IllegalStateException("Service validation failed. Cannot build service.");
        }

        Service service = new Service(
                sellerId,
                title,
                description != null ? description : "No description provided",
                category,
                basePrice,
                deliveryTime
        );

        System.out.println("Service '" + title + "' built successfully!");
        return service;
    }

    @Override
    public String toString() {
        return "ServiceBuilder{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", basePrice=$" + basePrice +
                '}';
    }
}