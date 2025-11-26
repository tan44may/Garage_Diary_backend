package com.garagediary.garagediary.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class AvailabilityRequest {
    private List<String> availableDays;
    private LocalTime startTime;
    private LocalTime endTime;
}
