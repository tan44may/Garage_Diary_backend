package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.ServiceCenter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class BookingRequestDto {

        private UUID serviceCenterId;
        private UUID serviceId;
        private UUID vehicleId;

        private LocalDate bookingDate;
        private LocalTime bookingTime;

        private String mobileNumber;
        private String customerName;
}

