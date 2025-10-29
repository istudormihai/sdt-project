package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {
    private String serviceId;
    private String sellerId;
    private String title;
    private String description;
    private String category;
    private double basePrice;
    private int deliveryTime;
    private Date createdDate;
    private boolean isActive;

    Service(String sellerId, String title, String description, String category,
            double basePrice, int deliveryTime) {
        this.serviceId = "SRV-" + System.currentTimeMillis();
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.basePrice = basePrice;
        this.deliveryTime = deliveryTime;
        this.createdDate = new Date();
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
        System.out.println("Service '" + title + "' has been activated.");
    }

    public void deactivate() {
        this.isActive = false;
        System.out.println("Service '" + title + "' has been deactivated.");
    }

    public String getDetails() {
        return "Service Details:\n" +
                "  ID: " + serviceId + "\n" +
                "  Title: " + title + "\n" +
                "  Category: " + category + "\n" +
                "  Base Price: $" + basePrice + "\n" +
                "  Delivery: " + deliveryTime + " days\n" +
                "  Status: " + (isActive ? "Active" : "Inactive");
    }

    public String getServiceId() { return serviceId; }
    public String getSellerId() { return sellerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public double getBasePrice() { return basePrice; }
    public int getDeliveryTime() { return deliveryTime; }
    public boolean isActive() { return isActive; }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", basePrice=$" + basePrice +
                ", isActive=" + isActive +
                '}';
    }
}