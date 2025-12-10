package com.skillsphere.catalogservice.service;

import com.skillsphere.catalogservice.domain.*;
import com.skillsphere.catalogservice.dto.CreateServiceRequest;
import com.skillsphere.catalogservice.dto.ServiceSummaryResponse;
import com.skillsphere.catalogservice.dto.TierQuoteResponse;
import com.skillsphere.catalogservice.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final ServiceRepository repo;

    public ServiceSummaryResponse create(CreateServiceRequest req) {
        ServiceEntity entity = new ServiceBuilder()
                .seller(req.sellerId())
                .title(req.title())
                .category(req.category())
                .description(req.description())
                .basePrice(req.basePrice())
                .deliveryTime(req.baseDeliveryDays())
                .build();
        ServiceEntity saved = repo.save(entity);
        return new ServiceSummaryResponse(saved.getId(), saved.getTitle(),
                saved.getCategory(), saved.getBasePrice());
    }

    public ServiceEntity getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
    }

    public TierQuoteResponse getTierQuote(Long id, String tier) {
        ServiceEntity service = getById(id);
        ServiceOffering base = new BaseServiceOffering(service);
        ServiceOffering offering = switch (tier.toUpperCase()) {
            case "SILVER" -> new SilverTierDecorator(base);
            case "GOLD" -> new GoldTierDecorator(base);
            default -> base;
        };
        return new TierQuoteResponse(
                service.getId(),
                tier.toUpperCase(),
                offering.getPrice(),
                offering.getDeliveryTimeDays(),
                offering.getFeatures()
        );
    }
}