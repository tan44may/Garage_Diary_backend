package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.ServiceCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, UUID> {

    List<ServiceCenter> findByOwnerId(UUID ownerId);

    @Query("""
        SELECT sc FROM ServiceCenter sc
        WHERE (
            6371 * acos(
                cos(radians(:lat)) * cos(radians(sc.latitude)) *
                cos(radians(sc.longitude) - radians(:lng)) +
                sin(radians(:lat)) * sin(radians(sc.latitude))
            )
        ) <= :radius
        """)
    Page<ServiceCenter> findNearbyServiceCenters(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radius") double radius,
            Pageable pageable
    );
    List<ServiceCenter> findByGarageNameContainingIgnoreCase(String keyword);

    Optional<ServiceCenter> findByEmail(String email);
}
