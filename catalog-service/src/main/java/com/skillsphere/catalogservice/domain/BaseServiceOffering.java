package com.skillsphere.catalogservice.domain;

import java.util.List;

public class BaseServiceOffering implements ServiceOffering {
    private final ServiceEntity service;

    public BaseServiceOffering(ServiceEntity service) { this.service = service; }

    public double getPrice() { return service.getBasePrice(); }
    public int getDeliveryTimeDays() { return service.getBaseDeliveryTimeDays(); }
    public List<String> getFeatures() { return List.of("Base delivery"); }
    public String getDescription() { return service.getDescription(); }
}
