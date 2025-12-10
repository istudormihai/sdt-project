package com.skillsphere.catalogservice.repository;

import com.skillsphere.catalogservice.domain.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByCategoryContainingIgnoreCase(String category);

    ServiceEntity save(ServiceEntity entity);
}
