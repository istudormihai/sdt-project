package entities;

import java.util.List;

public abstract class ServiceOfferingDecorator implements ServiceOffering {
    protected ServiceOffering wrappedOffering;

    public ServiceOfferingDecorator(ServiceOffering offering) {
        this.wrappedOffering = offering;
    }

    @Override
    public double getPrice() {
        return wrappedOffering.getPrice();
    }

    @Override
    public List<String> getFeatures() {
        return wrappedOffering.getFeatures();
    }

    @Override
    public int getDeliveryTime() {
        return wrappedOffering.getDeliveryTime();
    }

    @Override
    public String getDescription() {
        return wrappedOffering.getDescription();
    }
}