package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.SubscriptionPlanRepository;
import com.garagediary.garagediary.entity.SubscriptionPlan;
import com.garagediary.garagediary.service.SubscriptionPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SubscriptionPlanServiceImpl
        implements SubscriptionPlanService {

    private final SubscriptionPlanRepository repository;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriptionPlan createPlan(SubscriptionPlan plan) {
        plan.setCreatedAt(LocalDateTime.now());
        return repository.save(plan);
    }

    @Override
    public List<SubscriptionPlan> getActivePlans() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public SubscriptionPlan getPlanById(UUID planId) {
        return repository.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Plan not found"));
    }
}

