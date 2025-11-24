package com.skillsphere.catalogservice.domain;

import java.util.ArrayList;
import java.util.List;

public class GoldTierDecorator extends ServiceOfferingDecorator {
    public GoldTierDecorator(ServiceOffering wrapped) { super(wrapped); }
    @Override public double getPrice() { return wrapped.getPrice() + 50; }
    @Override public int getDeliveryTimeDays() { return Math.max(1, wrapped.getDeliveryTimeDays() - 1); }
    @Override public List<String> getFeatures() {
        var list = new ArrayList<>(wrapped.getFeatures());
        list.add("Priority support");
        return list;
    }
}
