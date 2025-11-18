package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.FeedbackRequestDto;
import com.garagediary.garagediary.dto.FeedbackResponseDto;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    FeedbackResponseDto addFeedback(FeedbackRequestDto dto);

    List<FeedbackResponseDto> getFeedbacksForServiceCenter(UUID serviceCenterId);

    List<FeedbackResponseDto> getFeedbacksByUser(UUID userId);

    double calculateAverageRating(UUID serviceCenterId);
}
