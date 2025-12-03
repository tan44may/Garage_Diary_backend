package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FeedbackResponseDto {

    private UUID id;
    private String comment;
    private int rating;
    private LocalDate feedbackDate;

    private UUID customerId;
    private ServiceCenterDto serviceCenter;
}
