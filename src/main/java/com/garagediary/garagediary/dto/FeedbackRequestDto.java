package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FeedbackRequestDto {
    private String comment;
    private int rating;
    private UUID customerId;
    private UUID serviceCenterId;
}
