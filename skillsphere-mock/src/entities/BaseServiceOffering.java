package entities;

import java.util.ArrayList;
import java.util.List;

public class BaseServiceOffering implements ServiceOffering {
    private Service service;
    private String tierName;
    private double basePrice;
    private List<String> baseFeatures;
    private int baseDeliveryTime;

    public BaseServiceOffering(Service service, String tierName) {
        this.service = service;
        this.tierName = tierName;
        this.basePrice = service.getBasePrice();
        this.baseDeliveryTime = service.getDeliveryTime();

        this.baseFeatures = new ArrayList<>();
        this.baseFeatures.add("Basic service delivery");
        this.baseFeatures.add("Standard support");
        this.baseFeatures.add(tierName + " tier access");

        System.out.println("Created BaseServiceOffering: " + tierName);
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    @Override
    public List<String> getFeatures() {
        return new ArrayList<>(baseFeatures);
    }

    @Override
    public int getDeliveryTime() {
        return baseDeliveryTime;
    }

    @Override
    public String getDescription() {
        return service.getTitle() + " - " + tierName + " Tier";
    }

    public Service getService() {
        return service;
    }

    @Override
    public String toString() {
        return "BaseServiceOffering{" +
                "tier='" + tierName + '\'' +
                ", price=$" + basePrice +
                ", deliveryTime=" + baseDeliveryTime + " days" +
                '}';
    }
}