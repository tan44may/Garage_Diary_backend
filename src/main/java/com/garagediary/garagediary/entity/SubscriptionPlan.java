package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.PlanName;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
//@Table(name = "subscription_plans")
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String planName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer durationDays;

    private String description;

    private Boolean isActive = true;

    private Integer maxServices;
    private Integer maxActiveBookings;

    private Boolean pickupServiceAllowed;
    private Boolean chatEnabled;
    private Boolean priorityListing;
    private Boolean analyticsAccess;

    private LocalDateTime createdAt;
}
