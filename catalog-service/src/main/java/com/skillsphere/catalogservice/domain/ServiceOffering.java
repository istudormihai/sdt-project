package com.skillsphere.catalogservice.domain;

import java.util.List;

public interface ServiceOffering {
    double getPrice();
    int getDeliveryTimeDays();
    List<String> getFeatures();
    String getDescription();
}