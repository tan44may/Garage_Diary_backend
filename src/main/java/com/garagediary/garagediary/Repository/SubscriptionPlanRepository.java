package com.garagediary.garagediary.Repository;


import com.garagediary.garagediary.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionPlanRepository
        extends JpaRepository<SubscriptionPlan, UUID> {

    Optional<SubscriptionPlan> findByPlanName(String planName);

    List<SubscriptionPlan> findByIsActiveTrue();
}
