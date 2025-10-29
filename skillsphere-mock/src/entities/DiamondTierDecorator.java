package entities;

import java.util.ArrayList;
import java.util.List;

public class DiamondTierDecorator extends ServiceOfferingDecorator {
    private static final double ADDITIONAL_PRICE = 100.0;
    private static final int DELIVERY_REDUCTION = 5;

    public DiamondTierDecorator(ServiceOffering offering) {
        super(offering);
        System.out.println("Adding Diamond Tier features (+$" + ADDITIONAL_PRICE + ", -" + DELIVERY_REDUCTION + " days)");
    }

    @Override
    public double getPrice() {
        return wrappedOffering.getPrice() + ADDITIONAL_PRICE;
    }

    @Override
    public List<String> getFeatures() {
        List<String> features = new ArrayList<>(wrappedOffering.getFeatures());
        features.add("Premium 24/7 support");
        features.add("Ultra-fast delivery");
        features.add("Unlimited revisions");
        features.add("Source files included");
        features.add("Commercial rights");
        features.add("All previous tier features included");
        return features;
    }

    @Override
    public int getDeliveryTime() {
        int baseDelivery = wrappedOffering.getDeliveryTime();
        return Math.max(1, baseDelivery - DELIVERY_REDUCTION);
    }

    @Override
    public String getDescription() {
        return wrappedOffering.getDescription() + " + Diamond Tier (Premium)";
    }

    @Override
    public String toString() {
        return "DiamondTierDecorator{additionalPrice=$" + ADDITIONAL_PRICE +
                ", deliveryReduction=" + DELIVERY_REDUCTION + " days}";
    }
}