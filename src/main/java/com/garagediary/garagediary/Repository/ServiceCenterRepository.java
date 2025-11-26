package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, UUID> {

    // Find all service centers owned by a specific owner
    List<ServiceCenter> findByOwnerId(UUID ownerId);

    // Search by name (case insensitive)
    List<ServiceCenter> findByGarageNameContainingIgnoreCase(String keyword);
}
