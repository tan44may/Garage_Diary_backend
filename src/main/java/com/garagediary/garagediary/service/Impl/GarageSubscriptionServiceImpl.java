package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.GarageSubscriptionRepository;
import com.garagediary.garagediary.Repository.SubscriptionPlanRepository;
import com.garagediary.garagediary.entity.GarageSubscription;
import com.garagediary.garagediary.entity.SubscriptionPlan;
import com.garagediary.garagediary.entity.enums.SubscriptionStatus;
import com.garagediary.garagediary.service.GarageSubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GarageSubscriptionServiceImpl
        implements GarageSubscriptionService {

    private final GarageSubscriptionRepository garageRepo;
    private final SubscriptionPlanRepository planRepo;

    public GarageSubscriptionServiceImpl(
            GarageSubscriptionRepository garageRepo,
            SubscriptionPlanRepository planRepo) {
        this.garageRepo = garageRepo;
        this.planRepo = planRepo;
    }

    @Override
    public GarageSubscription subscribeGarage(UUID garageId, UUID planId) {

        SubscriptionPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Plan not found"));

        garageRepo.findByGarageIdAndStatus(garageId, SubscriptionStatus.ACTIVE)
                .ifPresent(sub -> {
                    sub.setStatus(SubscriptionStatus.EXPIRED);
                    garageRepo.save(sub);
                });

        GarageSubscription subscription = new GarageSubscription();
        subscription.setGarageId(garageId);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(plan.getDurationDays()));
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setAutoRenew(false);

        return garageRepo.save(subscription);
    }

    @Override
    public GarageSubscription getActiveSubscription(UUID garageId) {
        return garageRepo.findByGarageIdAndStatus(
                garageId, SubscriptionStatus.ACTIVE
        ).orElseThrow(() -> new NoSuchElementException("No active subscription"));
    }

    @Override
    public void expireSubscription(UUID garageId) {
        GarageSubscription sub = getActiveSubscription(garageId);
        sub.setStatus(SubscriptionStatus.EXPIRED);
        garageRepo.save(sub);
    }
}

