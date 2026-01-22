package com.garagediary.garagediary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagediary.garagediary.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

        private Long bookingId;
        private Status status;

        private String customerName;
        private String serviceCenterName;
        private String serviceName;

        private LocalDate bookingDate;
        private LocalTime bookingTime;

        private String mobileNumber;
}
