package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.PlanName;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SubscriptionPlanResponseDto {
    private UUID id;
    private String description;
    private int durationInDays;
    private PlanName planName;
    private double price;
    private String expiryDate;   
}
