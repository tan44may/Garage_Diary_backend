package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ServiceCenterRequestDto {

    private String garageName;
    private double latitude;
    private double longitude;
    private String phone;

    private UUID ownerId;

    private List<String> availableDays;
    private LocalTime startTime;
    private LocalTime endTime;

    private String profileUrl;
    private String coverImgUrl;
    private List<String> gallery;
    private List<String> socialMedia;
}
