package entities;

import java.util.ArrayList;
import java.util.List;

public class BronzeTierDecorator extends ServiceOfferingDecorator {
    private static final double ADDITIONAL_PRICE = 0.0;

    public BronzeTierDecorator(ServiceOffering offering) {
        super(offering);
        System.out.println("Adding Bronze Tier features (+$" + ADDITIONAL_PRICE + ")");
    }

    @Override
    public double getPrice() {
        return wrappedOffering.getPrice() + ADDITIONAL_PRICE;
    }

    @Override
    public List<String> getFeatures() {
        List<String> features = new ArrayList<>(wrappedOffering.getFeatures());
        features.add("Basic support");
        return features;
    }

    @Override
    public String getDescription() {
        return wrappedOffering.getDescription() + " + Bronze Tier";
    }

    @Override
    public String toString() {
        return "BronzeTierDecorator{additionalPrice=$" + ADDITIONAL_PRICE + "}";
    }
}