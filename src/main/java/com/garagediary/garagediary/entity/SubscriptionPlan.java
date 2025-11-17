package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.PlanName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private int duration_in_days;

    @Enumerated(EnumType.STRING)
    private PlanName plan_name;

    private double price;
}
