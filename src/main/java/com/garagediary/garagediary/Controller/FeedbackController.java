package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.FeedbackRequestDto;
import com.garagediary.garagediary.dto.FeedbackResponseDto;
import com.garagediary.garagediary.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<FeedbackResponseDto> addFeedback(@RequestBody FeedbackRequestDto dto)
    {
        return ResponseEntity.ok(feedbackService.addFeedback(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedBackForServiceCenter(@PathVariable UUID id)
    {
        return ResponseEntity.ok(feedbackService.getFeedbacksForServiceCenter(id));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedBackByUser(@PathVariable UUID id)
    {
        return ResponseEntity.ok(feedbackService.getFeedbacksByUser(id));
    }

    @GetMapping("/rating/{id}")
    public ResponseEntity<Double> getRatingForServiceCenter(@PathVariable UUID id)
    {
        return ResponseEntity.ok(feedbackService.calculateAverageRating(id));
    }
}
