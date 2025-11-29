package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.FeedbackRepository;
import com.garagediary.garagediary.Repository.ServiceCenterRepository;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.dto.FeedbackRequestDto;
import com.garagediary.garagediary.dto.FeedbackResponseDto;
import com.garagediary.garagediary.dto.ServiceCenterDto;
import com.garagediary.garagediary.entity.Feedback;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.service.FeedbackService;
import com.garagediary.garagediary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final ServiceCenterRepository serviceCenterRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    @Override
    public FeedbackResponseDto addFeedback(FeedbackRequestDto dto) {

        Feedback feedback = new Feedback();
        feedback.setFeedbackDate(LocalDate.now());
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        ServiceCenter serviceCenter = serviceCenterRepository.findById(dto.getServiceCenterId()).orElseThrow(() -> new NoSuchElementException("Service center not found"));
        feedback.setServiceCenter(serviceCenter);
        UUID id= userService.findCurrentUser();
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        feedback.setCustomer(user);

        feedback = feedbackRepository.save(feedback);

        return convertToResponse(feedback);
    }

    private FeedbackResponseDto convertToResponse(Feedback feedback)
    {
        ServiceCenter serviceCenter = feedback.getServiceCenter();
        return FeedbackResponseDto.builder()
                .id(feedback.getId())
                .comment(feedback.getComment())
                .feedbackDate(feedback.getFeedbackDate())
                .customerId(feedback.getCustomer().getUser_id())
                .rating(feedback.getRating())
                .serviceCenter(new ServiceCenterDto(serviceCenter.getId(),serviceCenter.getGarageName(),serviceCenter.getPhone()))
                .build();
    }
    @Override
    public List<FeedbackResponseDto> getFeedbacksForServiceCenter(UUID serviceCenterId) {
        ServiceCenter serviceCenter = serviceCenterRepository.findById(serviceCenterId).orElseThrow(() -> new NoSuchElementException("Service center not found"));

        return List.of();
    }

    @Override
    public List<FeedbackResponseDto> getFeedbacksByUser(UUID userId) {
        return List.of();
    }

    @Override
    public double calculateAverageRating(UUID serviceCenterId) {
        return 0;
    }
}
