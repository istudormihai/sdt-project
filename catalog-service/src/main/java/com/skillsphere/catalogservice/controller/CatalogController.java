package com.skillsphere.catalogservice.controller;

import com.skillsphere.catalogservice.domain.ServiceEntity;
import com.skillsphere.catalogservice.dto.CreateServiceRequest;
import com.skillsphere.catalogservice.dto.ServiceSummaryResponse;
import com.skillsphere.catalogservice.dto.TierQuoteResponse;
import com.skillsphere.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService service;

    @PostMapping("/services")
    public ResponseEntity<ServiceSummaryResponse> create(@RequestBody CreateServiceRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping("/services/{id}")
    public ServiceEntity get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/services/{id}/tier/{tier}")
    public TierQuoteResponse quote(@PathVariable Long id, @PathVariable String tier) {
        return service.getTierQuote(id, tier);
    }
}
