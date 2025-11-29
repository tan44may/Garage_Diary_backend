package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.FeedbackRequestDto;
import com.garagediary.garagediary.dto.FeedbackResponseDto;
import com.garagediary.garagediary.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
