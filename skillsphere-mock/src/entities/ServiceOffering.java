package entities;

import java.util.List;

public interface ServiceOffering {
    double getPrice();
    List<String> getFeatures();
    int getDeliveryTime();
    String getDescription();
}