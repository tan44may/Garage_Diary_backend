package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, UUID> {

    List<ServiceCenter> findByOwnerId(UUID ownerId);

    List<ServiceCenter> findByGarageNameContainingIgnoreCase(String keyword);
}
