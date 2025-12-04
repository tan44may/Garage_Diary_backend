package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.ActivePlan;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceCenterResponseDto {

    private UUID id;

    private String garageName;
    private double latitude;
    private double longitude;
    private String phone;

    private UUID ownerId;

    private ActivePlan activePlan;
    private LocalDate planStartedDate;
    private LocalDate planEndDate;

    private List<String> availableDays;
    private LocalTime startTime;
    private LocalTime endTime;

    private float averageRating;

    private String profileUrl;
    private String coverImgUrl;
    private List<String> gallery;
    private List<String> socialMedia;
    private List<String> documents;

    private int totalServices;
    private int totalBookings;
    private String plan;
}
