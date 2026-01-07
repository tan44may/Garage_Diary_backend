package com.garagediary.garagediary.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
    private LocalDateTime timestamp;

    private String error;

    private HttpStatus status;
}

