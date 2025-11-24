package com.skillsphere.catalogservice.domain;

import java.util.List;

public abstract class ServiceOfferingDecorator implements ServiceOffering {
    protected final ServiceOffering wrapped;

    protected ServiceOfferingDecorator(ServiceOffering wrapped) {
        this.wrapped = wrapped;
    }
    public double getPrice() { return wrapped.getPrice(); }
    public int getDeliveryTimeDays() { return wrapped.getDeliveryTimeDays(); }
    public List<String> getFeatures() { return wrapped.getFeatures(); }
    public String getDescription() { return wrapped.getDescription(); }
}
