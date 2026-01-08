package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.SubscriptionPlanRequestDto;
import com.garagediary.garagediary.dto.SubscriptionPlanResponseDto;
import com.garagediary.garagediary.entity.SubscriptionPlan;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPlanService {

//    SubscriptionPlanResponseDto createSubscriptionPlan(SubscriptionPlanRequestDto dto);
//
//    SubscriptionPlanResponseDto getSubscriptionPlanById(UUID id);
//
//    List<SubscriptionPlanResponseDto> getAllSubscriptionPlans();
//
//    SubscriptionPlanResponseDto updateSubscriptionPlan(UUID id, SubscriptionPlanRequestDto dto);
//
//    String deleteSubscriptionPlan(UUID id);
//
//    String calculateExpiryDate(int durationInDays);

    SubscriptionPlan createPlan(SubscriptionPlan plan);

    List<SubscriptionPlan> getActivePlans();

    SubscriptionPlan getPlanById(UUID planId);
}
