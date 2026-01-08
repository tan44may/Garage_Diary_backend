package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.entity.SubscriptionPlan;
import com.garagediary.garagediary.service.SubscriptionPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService service;

    public SubscriptionPlanController(SubscriptionPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SubscriptionPlan> createPlan(
            @RequestBody SubscriptionPlan plan) {
        return ResponseEntity.ok(service.createPlan(plan));
    }



}

