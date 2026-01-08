package com.garagediary.garagediary.service;

import com.garagediary.garagediary.entity.GarageSubscription;

import java.util.UUID;

public interface GarageSubscriptionService {

    GarageSubscription subscribeGarage(UUID garageId, UUID planId);

    GarageSubscription getActiveSubscription(UUID garageId);

    void expireSubscription(UUID garageId);
}

