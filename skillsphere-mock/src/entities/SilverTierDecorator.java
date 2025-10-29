package entities;

import java.util.ArrayList;
import java.util.List;

public class SilverTierDecorator extends ServiceOfferingDecorator {
    private static final double ADDITIONAL_PRICE = 15.0;

    public SilverTierDecorator(ServiceOffering offering) {
        super(offering);
        System.out.println("Adding Silver Tier features (+$" + ADDITIONAL_PRICE + ")");
    }

    @Override
    public double getPrice() {
        return wrappedOffering.getPrice() + ADDITIONAL_PRICE;
    }

    @Override
    public List<String> getFeatures() {
        List<String> features = new ArrayList<>(wrappedOffering.getFeatures());
        features.add("High-resolution files");
        features.add("Faster Support response");
        features.add("Previous tier features included");
        return features;
    }

    @Override
    public String getDescription() {
        return wrappedOffering.getDescription() + " + Silver Tier";
    }

    @Override
    public String toString() {
        return "SilverTierDecorator{additionalPrice=$" + ADDITIONAL_PRICE + "}";
    }
}