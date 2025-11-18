package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.PlanName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionPlanRequestDto {
    private String description;
    private int durationInDays;
    private PlanName planName;
    private double price;
}
