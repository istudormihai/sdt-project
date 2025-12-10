package com.skillsphere.catalogservice.domain;

import java.util.ArrayList;
import java.util.List;

public class SilverTierDecorator extends ServiceOfferingDecorator {
    public SilverTierDecorator(ServiceOffering wrapped) { super(wrapped); }
    @Override public double getPrice() { return wrapped.getPrice() + 20; }
    @Override public List<String> getFeatures() {
        var list = new ArrayList<>(wrapped.getFeatures());
        list.add("Extra revision");
        return list;
    }
}