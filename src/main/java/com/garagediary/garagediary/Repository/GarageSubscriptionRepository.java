package com.garagediary.garagediary.Repository;

import com.garagediary.garagediary.entity.GarageSubscription;
import com.garagediary.garagediary.entity.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GarageSubscriptionRepository
        extends JpaRepository<GarageSubscription, Long> {

    Optional<GarageSubscription> findByGarageIdAndStatus(
            UUID garageId, SubscriptionStatus status
    );
}
