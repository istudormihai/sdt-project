package entities;

import java.util.ArrayList;
import java.util.List;

public class GoldTierDecorator extends ServiceOfferingDecorator {
    private static final double ADDITIONAL_PRICE = 30;
    private static final int DELIVERY_REDUCTION = 3;

    public GoldTierDecorator(ServiceOffering offering) {
        super(offering);
        System.out.println("Adding Gold Tier features (+$" + ADDITIONAL_PRICE + ", -" + DELIVERY_REDUCTION + " day)");
    }

    @Override
    public double getPrice() {
        return wrappedOffering.getPrice() + ADDITIONAL_PRICE;
    }

    @Override
    public List<String> getFeatures() {
        List<String> features = new ArrayList<>(wrappedOffering.getFeatures());
        features.add("Priority support");
        features.add("Expedited delivery");
        features.add("Multiple revisions");
        features.add("Previous tier features included");
        return features;
    }

    @Override
    public int getDeliveryTime() {
        int baseDelivery = wrappedOffering.getDeliveryTime();
        return Math.max(1, baseDelivery - DELIVERY_REDUCTION);
    }

    @Override
    public String getDescription() {
        return wrappedOffering.getDescription() + " + Gold Tier";
    }

    @Override
    public String toString() {
        return "GoldTierDecorator{additionalPrice=$" + ADDITIONAL_PRICE +
                ", deliveryReduction=" + DELIVERY_REDUCTION + " day}";
    }
}